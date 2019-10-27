package com.roc.api;

import com.google.gson.JsonObject;
import com.roc.exception.LbsServerException;
import com.roc.pojo.SysUser;
import com.roc.service.UserAttentionService;
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
import java.util.HashMap;
import java.util.Map;

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
    @Autowired
    private UserAttentionService userAttentionService;


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
                                 @RequestParam("userId")int userId,
                                 @ApiParam(value = "png/jpeg文件",name = "file")@RequestParam(value = "file",required=false)MultipartFile blobFile){
        Object redisValue= redisUtil.get(token);
        if (redisValue==null){
            //该用户未登录
            return JsonResult.error(ResultEnum.LOGIN_EXPIRE);
        }
        JsonObject jo=JsonUtil.parse(redisValue.toString());
        int redisUserId=Integer.valueOf(jo.get("userId").getAsString());
        if(userId!=redisUserId){
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
        SysUser user=userService.getUser(userId);
        user.setHeadImage(filePath);
        userService.updateUser(user);
        JsonResult jsonResult=JsonResult.putValue("iconUrl",filePath);
        return jsonResult.ok(jsonResult,ResultEnum.OPERATION_SUCCESS.getMsg());
    }

    @ApiOperation(value ="获取个人信息",response = JsonResult.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "用户Id",required = true),
                        @ApiImplicitParam(name = "token", value = "token令牌",required = true)})
    @RequestMapping(value = "/{userId}/userMsg",method = RequestMethod.GET)
    public JsonResult userMsg(@PathVariable("userId")int userId,@RequestHeader("token")String token){
        Object redisValue= redisUtil.get(token);
        if (redisValue==null){
            //该用户未登录
            return JsonResult.error(ResultEnum.LOGIN_EXPIRE);
        }
        JsonObject jo=JsonUtil.parse(redisValue.toString());
        int redisUserId=Integer.valueOf(jo.get("userId").getAsString());
        if(userId!=redisUserId){
            //前端传过来的用户id与token里存储的不同
            return JsonResult.error(ResultEnum.LOGIN_EXPIRE);
        }
        SysUser user = userService.getUser(userId);
        if(user==null){
            throw new LbsServerException(ResultEnum.OPERATION_FAILURE);
        }
        Map<String,Object> map=new HashMap<>(16);
        map.put("userName", user.getName());
        map.put("headImage",user.getHeadImage());
        map.put("msg",ResultEnum.OPERATION_SUCCESS.getMsg());
        return JsonResult.ok(map);
    }

    @ApiOperation(value = "关注用户功能",response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户Id",required = true),
            @ApiImplicitParam(name="followId",value = "被关注对象的Id",required = true),
            @ApiImplicitParam(name = "token", value = "token令牌",required = true)})
    @RequestMapping(value = "/attention",method = RequestMethod.POST)
    public JsonResult attention(@RequestParam("userId")int userId,@RequestParam("followId")int followId,@RequestHeader("token")String token){
        Object redisValue= redisUtil.get(token);
        if (redisValue==null){
            //该用户未登录
            return JsonResult.error(ResultEnum.LOGIN_EXPIRE);
        }
        JsonObject jo=JsonUtil.parse(redisValue.toString());
        int redisUserId=Integer.valueOf(jo.get("userId").getAsString());
        if(userId!=redisUserId){
            //前端传过来的用户id与token里存储的不同
            return JsonResult.error(ResultEnum.LOGIN_EXPIRE);
        }
        int i = userAttentionService.addAttention(userId, followId);
        if(i!=1){
            return JsonResult.error(ResultEnum.ATTENTION_FAILURE.getMsg());
        }
        return JsonResult.ok(ResultEnum.ATTENTION_SUCCESS.getMsg());
    }
}
