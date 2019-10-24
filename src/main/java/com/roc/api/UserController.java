package com.roc.api;

import com.google.gson.JsonObject;
import com.roc.exception.LbsServerException;
import com.roc.pojo.SysUser;
import com.roc.service.UserService;
import com.roc.utils.JsonResult;
import com.roc.utils.JsonUtil;
import com.roc.utils.RedisUtil;
import com.roc.utils.ResultEnum;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author p
 */
@Api(value = "/user",description = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;


    @ApiOperation(value = "用户注册功能",response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "昵称",required = true),
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true)})
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public JsonResult register(@RequestParam(name = "name")String name,
                               @RequestParam(name = "username")String username,
                               @RequestParam(name = "password")String password){
        SysUser user=new SysUser();
        user.setName(name);
        user.setUserName(username);
        user.setPassword(password);
        userService.registerUser(user);
        return JsonResult.ok(ResultEnum.REGISTER_SUCCESS.getMsg());
    }

    @ApiOperation(value = "更换头像功能",response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌",required = true),
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true)})
    @RequestMapping(value = "/updateIcon",method = RequestMethod.POST)
    public JsonResult updateIcon(@RequestHeader("token")String token,
                                 @RequestParam("userId")String userId,
                                 @ApiParam(value = "png/jpeg文件",name = "file")@RequestParam(value = "file",required=false)MultipartFile blobFile){
        Object redisValue= redisUtil.get(token);
        if (redisValue==null){
            //该用户未登录
            return JsonResult.error(ResultEnum.LOGIN_EXPIRE);
        }
        JsonObject jo=JsonUtil.parse(redisValue.toString());
        String redisUserId=jo.get("userId").getAsString();
        if(!(userId.equals(redisUserId))){
            //前端传过来的用户id与token里存储的不同
            return JsonResult.error(ResultEnum.LOGIN_EXPIRE);
        }
        if(blobFile==null){
            throw new LbsServerException(ResultEnum.FILE_UPLOAD_FAILURE);
        }
        long fileSize=blobFile.getSize();
        long defaultFileSize=3145728;
        if(fileSize>defaultFileSize){
            throw new LbsServerException(ResultEnum.FILE_SIZE_TOO_BIG);
        }
        String fileName=blobFile.getOriginalFilename();
        String subffix=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
        String newFileName=token;
        //路径待修改,到时候指定到linux的某个地方
        String filePath="D:\\"+newFileName+"."+subffix;
        File file=new File(filePath);
        try {
            blobFile.transferTo(file);
        } catch (IOException e) {
           throw new LbsServerException(ResultEnum.FILE_UPLOAD_FAILURE);
        }
        //对数据库修改
        SysUser user=userService.getUser(Integer.valueOf(userId));
        user.setHeadImage(filePath);
        userService.updateUser(user);
        redisUtil.set(token, JsonUtil.obj2String(user),60*60*24);
        JsonResult jsonResult=JsonResult.putValue("iconUrl",filePath);
        return jsonResult.ok(jsonResult,ResultEnum.OPERATION_SUCCESS.getMsg());
    }
}
