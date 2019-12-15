package com.roc.api;

import com.roc.exception.LbsServerException;
import com.roc.pojo.Experience;
import com.roc.pojo.Reply;
import com.roc.service.ExperienceService;
import com.roc.service.ReplyService;
import com.roc.utils.JsonResult;
import com.roc.utils.ResultEnum;
import com.roc.utils.UserUtil;
import com.roc.vo.ExperienceVo;
import com.roc.vo.ReplyVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Roc
 * @version 1.0
 * @date 2019/10/30 22:30
 * @description 心得交流接口
 */
@Api(value = "/experience",description = "心得交流接口")
@RestController
@RequestMapping("/experience")
public class ExperienceApi {

    @Autowired
    private ExperienceService experienceService;
    @Autowired
    private UserUtil userUtil;
    @Autowired
    private ReplyService replyService;

    @ApiOperation(value = "心得交流首页",response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌",required = true),
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true)})
    @RequestMapping(value = "/experienceHome",method = RequestMethod.GET)
    public JsonResult experienceHome(@RequestHeader("token")String token,
                                 @RequestParam("userId")int userId){
        ResultEnum resultEnum = userUtil.checkToken(userId, token);
        if(resultEnum!=null){
            return JsonResult.error(resultEnum);
        }
        List<ExperienceVo> markList = experienceService.findAllList();
        Map<String,Object> map=new HashMap<>(16);
        map.put("count",markList.size());
        map.put("result",markList);
        map.put("msg",ResultEnum.OPERATION_SUCCESS.getMsg());
        return JsonResult.ok(map);
    }

    @ApiOperation(value = "发布心得",response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌",required = true),
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true),
            @ApiImplicitParam(name = "title", value = "心得标题", required = true),
            @ApiImplicitParam(name = "content", value = "心得内容", required = true)})
    @RequestMapping(value = "/postExperience",method = RequestMethod.POST)
    public JsonResult postExperience(@RequestHeader("token")String token, @RequestParam("userId")int userId,
                                     @RequestParam("title")String title, @RequestParam("content")String content){
        ResultEnum resultEnum = userUtil.checkToken(userId, token);
        if(resultEnum!=null){
            return JsonResult.error(resultEnum);
        }
        Experience experience=new Experience();
        experience.setContent(content);
        experience.setUserId(userId);
        experience.setTitle(title);
        int i = experienceService.postExperience(experience);
        if(i!=1){
            throw new LbsServerException(ResultEnum.PUBLIC_FAILURE);
        }
        return JsonResult.ok(ResultEnum.PUBLIC_SUCCESS.getMsg());
    }

    @ApiOperation(value = "回复心得",response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌",required = true),
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true),
            @ApiImplicitParam(name = "commId", value = "心得Id", required = true),
            @ApiImplicitParam(name = "content", value = "回复内容", required = true)})
    @RequestMapping(value = "/replyExperience",method = RequestMethod.POST)
    public JsonResult replyExperience(@RequestHeader("token")String token, @RequestParam("userId")int userId,
                                      @RequestParam("commId")int commId,@RequestParam("content")String content){
        ResultEnum resultEnum = userUtil.checkToken(userId, token);
        if(resultEnum!=null){
            return JsonResult.error(resultEnum);
        }
        Reply reply=new Reply();
        reply.setContent(content);
        reply.setUserId(userId);
        reply.setExperienceId(commId);
        int i = replyService.replyExperience(reply);
        if(i!=1){
            throw new LbsServerException(ResultEnum.REPLY_FAILURE);
        }
        return JsonResult.ok(ResultEnum.REPLY_SUCCESS.getMsg());
    }

    @ApiOperation(value = "点赞心得",response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌",required = true),
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true),
            @ApiImplicitParam(name = "commId", value = "心得Id", required = true),
            @ApiImplicitParam(name = "likeFlag", value = "点赞标记,1点赞，-1取消点赞，默认0", required = true),
            @ApiImplicitParam(name = "unlikeFlag", value = "踩标记,1踩，-1取消踩，默认0", required = true)})
    @RequestMapping(value = "/likeExperience",method = RequestMethod.POST)
    public JsonResult likeExperience(@RequestHeader("token")String token, @RequestParam("userId")int userId,
                                      @RequestParam("commId")int commId,@RequestParam("likeFlag")int likeFlag,
                                     @RequestParam("unlikeFlag")int unlikeFlag){
        ResultEnum resultEnum = userUtil.checkToken(userId, token);
        if(resultEnum!=null){
            return JsonResult.error(resultEnum);
        }
        Experience experience=new Experience();
        experience.setExperienceId(commId);
        experience.setUnlikeNum(unlikeFlag);
        experience.setLikeNum(likeFlag);
        int i = experienceService.updateLikeNumAndUnlikeNum(experience);
        if(i!=1){
            throw new LbsServerException(ResultEnum.OPERATION_FAILURE);
        }
        return JsonResult.ok(ResultEnum.OPERATION_SUCCESS.getMsg());
    }

    @ApiOperation(value = "心得交流详情",response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌",required = true),
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true),
            @ApiImplicitParam(name = "commId", value = "心得Id", required = true)})
    @RequestMapping(value = "/experienceDetail",method = RequestMethod.POST)
    public JsonResult experienceDetail(@RequestHeader("token")String token, @RequestParam("userId")int userId,
                                     @RequestParam("commId")int commId){
        ResultEnum resultEnum = userUtil.checkToken(userId, token);
        if(resultEnum!=null){
            return JsonResult.error(resultEnum);
        }
        Experience experience = experienceService.findExperience(commId);
        if(experience==null){
            throw new LbsServerException(ResultEnum.OPERATION_FAILURE);
        }
        List<ReplyVo> listByCommId = replyService.findListByCommId(commId);
        Map<String,Object> map =new HashMap<>(16);
        map.put("count",listByCommId.size());
        map.put("result",listByCommId);
        map.put("title",experience.getTitle());
        map.put("content",experience.getContent());
        map.put("likeNum",experience.getLikeNum());
        map.put("unlikeNum",experience.getUnlikeNum());
        map.put("createTime",experience.getCreateTime());
        map.put("msg",ResultEnum.OPERATION_SUCCESS.getMsg());
        return JsonResult.ok(map);
    }

}
