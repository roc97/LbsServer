package com.roc.api;

import com.google.gson.JsonObject;
import com.roc.exception.LbsServerException;
import com.roc.pojo.SysUser;
import com.roc.service.UserService;
import com.roc.utils.JsonResult;
import com.roc.utils.JsonUtil;
import com.roc.utils.RedisUtil;
import com.roc.utils.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author p
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;


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

    @RequestMapping(value = "/updateIcon",method = RequestMethod.POST)
    public JsonResult updateIcon(@RequestHeader("token")String token,
                                 @RequestParam("userId")String userId,
                                 @RequestParam(value = "file",required=false)MultipartFile blobFile){
        Object redisValue= redisUtil.get(token);
        if (redisValue==null){
            return JsonResult.error(ResultEnum.LOGIN_EXPIRE);
        }
        JsonObject jo=JsonUtil.parse(redisValue.toString());
        String redisUserId=jo.get("userId").getAsString();
        if(!(userId.equals(redisUserId))){
            //不存在该用户或者前端传过来的用户id与token里存储的不同
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
