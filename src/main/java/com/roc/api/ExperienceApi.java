package com.roc.api;

import com.roc.exception.LbsServerException;
import com.roc.pojo.Experience;
import com.roc.service.ExperienceService;
import com.roc.utils.JsonResult;
import com.roc.utils.ResultEnum;
import com.roc.utils.UserUtil;
import com.roc.vo.ExperienceVo;
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
        if(i!=0){
            throw new LbsServerException(ResultEnum.PUBLIC_FAILURE);
        }
        return JsonResult.ok(ResultEnum.PUBLIC_SUCCESS.getMsg());
    }
}
