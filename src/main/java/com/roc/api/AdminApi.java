package com.roc.api;

import com.roc.exception.LbsServerException;
import com.roc.pojo.MapMarkMessage;
import com.roc.service.ExperienceService;
import com.roc.service.MarkService;
import com.roc.service.UserService;
import com.roc.utils.JsonResult;
import com.roc.utils.ResultEnum;
import com.roc.utils.UserUtil;
import com.roc.vo.ExperienceVo;
import com.roc.vo.MarkCheckVo;
import com.roc.vo.MarkVo;
import com.roc.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Roc
 * @version 1.0
 * @date 2019/12/15 22:23
 * @description
 */
@Api(value = "/admin", description = "用户接口")
@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminApi {

    @Autowired
    private UserService userService;
    @Autowired
    private UserUtil userUtil;
    @Autowired
    private MarkService markService;
    @Autowired
    private ExperienceService experienceService;

    @ApiOperation(value = "查看用户所有信息", response = JsonResult.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "用户Id", required = true),
            @ApiImplicitParam(name = "token", value = "token令牌", required = true)})
    @RequestMapping(value = "/{userId}/userList", method = RequestMethod.POST)
    public JsonResult userList(@PathVariable("userId") int userId, @RequestHeader("token") String token) {
        userUtil.checkToken(userId, token);
        Map<String, Object> map = userService.findUserList();
        return JsonResult.ok(map);
    }

    @ApiOperation(value = "推广信息审核列表", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true),
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true)})
    @RequestMapping(value = "/pcCheckList", method = RequestMethod.GET)
    public JsonResult pcCheckList(@RequestHeader("token") String token,
                                  @RequestParam("userId") int userId) {
        userUtil.checkToken(userId, token);
        Map<String, Object> map = markService.getMarkCheckData();
        return JsonResult.ok(map);
    }

    @ApiOperation(value = "审核功能", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true),
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true),
            @ApiImplicitParam(name = "markId", value = "标注Id", required = true),
            @ApiImplicitParam(name = "status", value = "审核状态", required = true)})
    @RequestMapping(value = "/markCheck", method = RequestMethod.POST)
    public JsonResult markCheck(@RequestHeader("token") String token,
                                @RequestParam("userId") int userId,
                                @RequestParam("markId") int markId,
                                @RequestParam("status") int status){
        int selectStatus = 3;
        userUtil.checkToken(userId, token);
        markService.updateMarkStatus(markId, status, selectStatus);
        return JsonResult.ok(ResultEnum.OPERATION_SUCCESS);
    }

    @ApiOperation(value = "心得交流管理列表", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true),
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true),
            @ApiImplicitParam(name = "selectedUserId", value = "被查看用户id", required = true)})
    @RequestMapping(value = "/findExperienceList", method = RequestMethod.POST)
    public JsonResult findExperienceList(@RequestHeader("token") String token,
                                         @RequestParam("userId") int userId,
                                         @RequestParam("selectedUserId") int selectedUserId) {
        userUtil.checkToken(userId, token);
        Map<String, Object> map = experienceService.findExperienceAdminData(selectedUserId);
        return JsonResult.ok(map);
    }

    @ApiOperation(value = "标注地点管理列表", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌", required = true),
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true),
            @ApiImplicitParam(name = "selectedUserId", value = "被查看用户id", required = true)})
    @RequestMapping(value = "/findMarkList", method = RequestMethod.POST)
    public JsonResult findMarkList(@RequestHeader("token") String token,
                                   @RequestParam("userId") int userId,
                                   @RequestParam("selectedUserId") int selectedUserId) {
        userUtil.checkToken(userId, token);
        Map<String, Object> map = markService.findMarkAdminData(selectedUserId);
        return JsonResult.ok(map);
    }

    @ApiOperation(value = "心得交流/标注地点删除操作", response = JsonResult.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token令牌", required = true),
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true),
            @ApiImplicitParam(name = "type", value = "删除操作的类型", required = true),
            @ApiImplicitParam(name = "id", value = "被删除信息的id", required = true)})
    @RequestMapping(value = "/deleteByType", method = RequestMethod.POST)
    public JsonResult deleteByType(@RequestHeader("token") String token,
                                   @RequestParam("userId") int userId,
                                   @RequestParam("type") String type,
                                   @RequestParam("id") int id) throws LbsServerException {
        userUtil.checkToken(userId, token);
        userService.deleteMarkOrExperience(id, type);
        return JsonResult.ok(ResultEnum.OPERATION_SUCCESS);
    }

    @ApiOperation(value = "统计数据，状态区分，推广信息传1，心得交流传0，标注传-1", response = JsonResult.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "token令牌", required = true),
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = true),
            @ApiImplicitParam(name = "status", value = "状态区分标记", required = true)})
    @RequestMapping(value = "/countData", method = RequestMethod.POST)
    public JsonResult countData(@RequestHeader("token") String token, @RequestParam("userId") int userId,
                                @RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime,
                                @RequestParam("status") int status) {
        userUtil.checkToken(userId, token);
        Map<String, Object> map = userService.countData(status, startTime, endTime);
        return JsonResult.ok(map);
    }
}
