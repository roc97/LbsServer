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
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author p
 */
@Api(value = "/user", description = "用户接口")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserApi {

    @Autowired
    private UserService userService;
    @Autowired
    private UserUtil userUtil;
    @Autowired
    private UserAttentionService userAttentionService;


    @ApiOperation(value = "用户注册功能", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "昵称", required = true),
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true)})
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public JsonResult register(@RequestParam(name = "name") String name,
                               @RequestParam(name = "username") String username,
                               @RequestParam(name = "password") String password) {
        userService.registerUser(name, username, password);
        return JsonResult.ok(ResultEnum.REGISTER_SUCCESS);
    }

    @ApiOperation(value = "更换头像功能", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true),
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true)})
    @RequestMapping(value = "/updateIcon", method = RequestMethod.POST)
    public JsonResult updateIcon(@RequestHeader("token") String token,
                                 @RequestParam("userId") int userId,
                                 @ApiParam(value = "png/jpeg文件", name = "file") @RequestParam(value = "file", required = false) MultipartFile blobFile) {
        userUtil.checkToken(userId, token);
        String imageUrl = userService.headImageUpload(blobFile, token, userId);
        JsonResult jsonResult = JsonResult.putValue("iconUrl", imageUrl);
        return jsonResult.ok(jsonResult, ResultEnum.OPERATION_SUCCESS.getMsg());
    }

    @ApiOperation(value = "获取个人信息", response = JsonResult.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "用户Id", required = true),
            @ApiImplicitParam(name = "token", value = "token令牌", required = true)})
    @RequestMapping(value = "/{userId}/userMsg", method = RequestMethod.GET)
    public JsonResult userMsg(@PathVariable("userId") int userId, @RequestHeader("token") String token) {
        userUtil.checkToken(userId, token);
        Map<String, Object> map = userService.getUser(userId);
        return JsonResult.ok(map);
    }

    @ApiOperation(value = "关注用户功能", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true),
            @ApiImplicitParam(name = "followId", value = "被关注对象的Id", required = true),
            @ApiImplicitParam(name = "token", value = "token令牌", required = true)})
    @RequestMapping(value = "/attention", method = RequestMethod.POST)
    public JsonResult attention(@RequestParam("userId") int userId, @RequestParam("followId") int followId, @RequestHeader("token") String token) {
        userUtil.checkToken(userId, token);
        userAttentionService.addAttention(userId, followId);
        return JsonResult.ok(ResultEnum.ATTENTION_SUCCESS.getMsg());
    }

    @ApiOperation(value = "取消关注功能", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true),
            @ApiImplicitParam(name = "cancelId", value = "被取消关注对象的Id", required = true),
            @ApiImplicitParam(name = "token", value = "token令牌", required = true)})
    @RequestMapping(value = "/cancelAttention", method = RequestMethod.POST)
    public JsonResult cancelAttention(@RequestParam("userId") int userId, @RequestParam("cancelId") int cancelId,
                                      @RequestHeader("token") String token) {
        userUtil.checkToken(userId, token);
        userAttentionService.cancelAttention(userId, cancelId);
        return JsonResult.ok(ResultEnum.CANCEL_SUCCESS.getMsg());
    }

    @ApiOperation(value = "获取关注信息流", response = JsonResult.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "用户Id", required = true),
            @ApiImplicitParam(name = "token", value = "token令牌", required = true)})
    @RequestMapping(value = "/{userId}/attentionMsgList", method = RequestMethod.GET)
    public JsonResult attentionMsgList(@PathVariable("userId") int userId, @RequestHeader("token") String token) {
        userUtil.checkToken(userId, token);
        Map<String, Object> map = userAttentionService.getAttentionList(userId);
        return JsonResult.ok(map);
    }
}
