package com.roc.api;

import com.roc.exception.LbsServerException;
import com.roc.pojo.SysUser;
import com.roc.service.ExperienceService;
import com.roc.service.MarkService;
import com.roc.service.UserAttentionService;
import com.roc.service.UserService;
import com.roc.utils.JsonResult;
import com.roc.utils.ResultEnum;
import com.roc.utils.UserUtil;
import com.roc.vo.UserVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author p
 */
@Api(value = "/user",description = "用户接口")
@RestController
@RequestMapping("/user")
public class UserApi {

    @Autowired
    private UserService userService;
    @Autowired
    private UserUtil userUtil;
    @Autowired
    private UserAttentionService userAttentionService;
    @Autowired
    private MarkService markService;
    @Autowired
    private ExperienceService experienceService;


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
        ResultEnum resultEnum = userUtil.checkToken(userId, token);
        if(resultEnum!=null){
            return JsonResult.error(resultEnum);
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
        String basePath = ClassUtils.getDefaultClassLoader().getResource("static/image").getPath();
        String filePath=basePath+newFileName+"."+subffix;
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
        ResultEnum resultEnum = userUtil.checkToken(userId, token);
        if(resultEnum!=null){
            return JsonResult.error(resultEnum);
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
        ResultEnum resultEnum = userUtil.checkToken(userId, token);
        if(resultEnum!=null){
            return JsonResult.error(resultEnum);
        }
        int i = userAttentionService.addAttention(userId, followId);
        if(i!=1){
            return JsonResult.error(ResultEnum.ATTENTION_FAILURE.getMsg());
        }
        return JsonResult.ok(ResultEnum.ATTENTION_SUCCESS.getMsg());
    }

    @ApiOperation(value = "取消关注功能",response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户Id",required = true),
            @ApiImplicitParam(name="cancelId",value = "被取消关注对象的Id",required = true),
            @ApiImplicitParam(name = "token", value = "token令牌",required = true)})
    @RequestMapping(value = "/cancelAttention",method = RequestMethod.POST)
    public JsonResult cancelAttention(@RequestParam("userId")int userId,@RequestParam("cancelId")int cancelId,@RequestHeader("token")String token){
        ResultEnum resultEnum = userUtil.checkToken(userId, token);
        if(resultEnum!=null){
            return JsonResult.error(resultEnum);
        }
        if ((userId<=0) || (cancelId<=0)){
            throw new LbsServerException(ResultEnum.JSON_PARSE_EXCEPTION);
        }
        int i = userAttentionService.cancelAttention(userId, cancelId);
        if(i!=1){
            return JsonResult.error(ResultEnum.OPERATION_FAILURE.getMsg());
        }
        return JsonResult.ok(ResultEnum.CANCEL_SUCCESS.getMsg());
    }

    @ApiOperation(value ="获取关注信息流",response = JsonResult.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "用户Id",required = true),
            @ApiImplicitParam(name = "token", value = "token令牌",required = true)})
    @RequestMapping(value = "/{userId}/attentionMsgList",method = RequestMethod.GET)
    public JsonResult attentionMsgList(@PathVariable("userId")int userId,@RequestHeader("token")String token){
        ResultEnum resultEnum = userUtil.checkToken(userId, token);
        if(resultEnum!=null){
            return JsonResult.error(resultEnum);
        }
        Map<String, Object> result = userAttentionService.getListByUserId(userId);
        int count= (int) result.get("count");
        result.remove("count");
        Map<String,Object> map=new HashMap<>(16);
        map.put("result", result);
        map.put("count",count);
        map.put("msg",ResultEnum.OPERATION_SUCCESS.getMsg());
        return JsonResult.ok(map);
    }

    @ApiOperation(value ="查看用户所有信息",response = JsonResult.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "用户Id",required = true),
            @ApiImplicitParam(name = "token", value = "token令牌",required = true)})
    @RequestMapping(value = "/{userId}/userList",method = RequestMethod.POST)
    public JsonResult userList(@PathVariable("userId")int userId,@RequestHeader("token")String token){
        ResultEnum resultEnum = userUtil.checkToken(userId, token);
        if(resultEnum!=null){
            return JsonResult.error(resultEnum);
        }
        List<UserVo> userList = userService.findUserList();
        Map<String,Object> map=new HashMap<>(16);
        map.put("count",userList.size());
        map.put("result",userList);
        map.put("status", HttpStatus.OK.value());
        map.put("msg",ResultEnum.OPERATION_SUCCESS.getMsg());
        return JsonResult.ok(map);
    }

    @ApiOperation(value ="心得交流/标注地点删除操作",response = JsonResult.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token令牌",required = true),
            @ApiImplicitParam(name = "userId", value = "用户Id",required = true),
            @ApiImplicitParam(name = "type", value = "删除操作的类型",required = true),
            @ApiImplicitParam(name = "id", value = "被删除信息的id",required = true)})
    @RequestMapping(value = "/deleteByType",method = RequestMethod.POST)
    public JsonResult deleteByType(@RequestHeader("token")String token,
                                   @RequestParam("userId")int userId,
                                   @RequestParam("type")String type,
                                   @RequestParam("id")int id) throws LbsServerException{
        ResultEnum resultEnum = userUtil.checkToken(userId, token);
        if(resultEnum!=null){
            return JsonResult.error(resultEnum);
        }
        String comm="comm",mark="mark";
        if(type.equals(comm)){
            experienceService.deleteExperience(id);
        }else if(type.equals(mark)){
            markService.deleteMark(id);
        }else{
            return JsonResult.error(ResultEnum.OPERATION_FAILURE);
        }
        return JsonResult.ok();
    }

    @ApiOperation(value ="统计数据，状态区分，推广信息传1，心得交流传0，标注传-1",response = JsonResult.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token令牌",required = true),
            @ApiImplicitParam(name = "userId", value = "用户Id",required = true),
            @ApiImplicitParam(name = "startTime", value = "开始时间",required = true),
            @ApiImplicitParam(name = "endTime", value = "结束时间",required = true),
            @ApiImplicitParam(name = "status", value = "状态区分标记",required = true)})
    @RequestMapping(value = "/countData",method = RequestMethod.POST)
    public JsonResult countData(@RequestHeader("token")String token,@RequestParam("userId")int userId,
                                @RequestParam("startTime")String startTime,@RequestParam("endTime")String endTime,
                                @RequestParam("status")int status){
        ResultEnum resultEnum = userUtil.checkToken(userId, token);
        if(resultEnum!=null){
            return JsonResult.error(resultEnum);
        }
        List<Map> result=new ArrayList<>();
        if(status!=0){
            result = markService.countTimeSlotRecords(status, startTime, endTime);
        }else{
            result=experienceService.countTimeSlotRecords(startTime,endTime);
        }
        Map<String,Object> map=new HashMap<>(16);
        map.put("count",result.size());
        map.put("result",result);
        map.put("status", HttpStatus.OK.value());
        map.put("msg",ResultEnum.OPERATION_SUCCESS.getMsg());
        return JsonResult.ok(map);
    }
}
