package com.roc.api;

import com.roc.service.ExperienceService;
import com.roc.service.ReplyService;
import com.roc.utils.JsonResult;
import com.roc.utils.ResultEnum;
import com.roc.utils.UserUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author Roc
 * @version 1.0
 * @date 2019/10/30 22:30
 * @description 心得交流接口
 */
@Slf4j
@Api(value = "/experience", description = "心得交流接口")
@RestController
@RequestMapping("/experience")
public class ExperienceApi {

    @Autowired
    private ExperienceService experienceService;
    @Autowired
    private UserUtil userUtil;
    @Autowired
    private ReplyService replyService;

    @ApiOperation(value = "心得交流首页", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true),
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true)})
    @RequestMapping(value = "/experienceHome", method = RequestMethod.GET)
    public JsonResult experienceHome(@RequestHeader("token") String token,
                                     @RequestParam("userId") int userId) {
        userUtil.checkToken(userId, token);
        Map<String, Object> map = experienceService.findExperienceHomeData(userId);
        return JsonResult.ok(map);
    }

    @ApiOperation(value = "发布心得", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true),
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true),
            @ApiImplicitParam(name = "title", value = "心得标题", required = true),
            @ApiImplicitParam(name = "content", value = "心得内容", required = true)})
    @RequestMapping(value = "/postExperience", method = RequestMethod.POST)
    public JsonResult postExperience(@RequestHeader("token") String token, @RequestParam("userId") int userId,
                                     @RequestParam("title") String title, @RequestParam("content") String content,
                                     @ApiParam(value = "png/jpeg文件", name = "file") @RequestParam(value = "file", required = false) MultipartFile file) {
        userUtil.checkToken(userId, token);
        experienceService.postExperience(userId, content, title,file,token);
        return JsonResult.ok(ResultEnum.PUBLIC_SUCCESS);
    }

    @ApiOperation(value = "回复心得", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true),
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true),
            @ApiImplicitParam(name = "commId", value = "心得Id", required = true),
            @ApiImplicitParam(name = "content", value = "回复内容", required = true)})
    @RequestMapping(value = "/replyExperience", method = RequestMethod.POST)
    public JsonResult replyExperience(@RequestHeader("token") String token, @RequestParam("userId") int userId,
                                      @RequestParam("commId") int commId, @RequestParam("content") String content) {
        userUtil.checkToken(userId, token);
        replyService.replyExperience(userId, commId, content);
        return JsonResult.ok(ResultEnum.REPLY_SUCCESS);
    }

    @ApiOperation(value = "点赞(踩)功能", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true),
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true),
            @ApiImplicitParam(name = "targetId", value = "目标Id", required = true),
            @ApiImplicitParam(name = "targetType", value = "目标类型 0心得，1回复", required = true),
            @ApiImplicitParam(name = "operateType", value = "操作类型,0点赞，1踩,-1取消操作", required = true)})
    @RequestMapping(value = "/likeOrHate", method = RequestMethod.POST)
    public JsonResult likeOrHate(@RequestHeader("token") String token, @RequestParam("userId") int userId,
                                 @RequestParam("targetId") int targetId, @RequestParam("targetType") int targetType,
                                 @RequestParam("operateType") int operateType) {
        userUtil.checkToken(userId, token);
        experienceService.likeOrHate(userId, targetId, targetType, operateType);
        return JsonResult.ok(ResultEnum.OPERATION_SUCCESS);
    }

    @ApiOperation(value = "心得交流详情", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true),
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true),
            @ApiImplicitParam(name = "commId", value = "心得Id", required = true)})
    @RequestMapping(value = "/experienceDetail", method = RequestMethod.POST)
    public JsonResult experienceDetail(@RequestHeader("token") String token, @RequestParam("userId") int userId,
                                       @RequestParam("commId") int commId) {
        userUtil.checkToken(userId, token);
        Map<String, Object> map=experienceService.findExperienceDetail(userId, commId);
        return JsonResult.ok(map);
    }

}
