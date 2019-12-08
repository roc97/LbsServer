package com.roc.api;

import com.roc.exception.LbsServerException;
import com.roc.pojo.MapMarkMessage;
import com.roc.service.MarkService;
import com.roc.utils.JsonResult;
import com.roc.utils.ResultEnum;
import com.roc.utils.UserUtil;
import com.roc.vo.MarkCheckVo;
import com.roc.vo.MarkVo;
import com.roc.vo.PublicCheckVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
 * @date 2019/10/29 20:20
 * @description 标注地点和推广相关的接口
 */
@Api(value = "/mark",description = "首页和推广流接口")
@RestController
@RequestMapping("/mark")
public class MarkMessageApi {

    @Autowired
    private MarkService markService;
    @Autowired
    private UserUtil userUtil;

    @ApiOperation(value = "发布信息功能",response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌",required = true),
            @ApiImplicitParam(name = "userId", value = "用户Id",required = true),
            @ApiImplicitParam(name = "lat", value = "纬度", required = true),
            @ApiImplicitParam(name = "lng", value = "经度", required = true),
            @ApiImplicitParam(name = "title", value = "标题", required = true),
            @ApiImplicitParam(name = "content", value = "内容", required = true)})
    @RequestMapping(value = "/publicMessage",method = RequestMethod.POST)
    public JsonResult publicMessage(@RequestHeader("token")String token, @RequestParam("userId")int userId,
                                 @RequestParam("lat")double lat, @RequestParam("lng")double lng,
                                 @RequestParam("title")String title,@RequestParam("content")String content){
        ResultEnum resultEnum = userUtil.checkToken(userId, token);
        if(resultEnum!=null){
            return JsonResult.error(resultEnum);
        }
        MapMarkMessage message=new MapMarkMessage();
        //未推广
        message.setStatus(0);
        message.setLng(lng);
        message.setLat(lat);
        message.setUserId(userId);
        message.setTitle(title);
        message.setContent(content);
        int i = markService.publicMessage(message);
        if (i!=1){
            throw new LbsServerException(ResultEnum.PUBLIC_FAILURE);
        }
        return JsonResult.ok(ResultEnum.PUBLIC_SUCCESS.getMsg());
    }

    @ApiOperation(value = "首页功能",response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌",required = true),
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true)})
    @RequestMapping(value = "/mobileHome",method = RequestMethod.GET)
    public JsonResult mobileHome(@RequestHeader("token")String token,
                                 @RequestParam("userId")int userId){
        ResultEnum resultEnum = userUtil.checkToken(userId, token);
        if(resultEnum!=null){
            return JsonResult.error(resultEnum);
        }
        List<MarkVo> markList = markService.findMarkList();
        Map<String,Object> map=new HashMap<>(16);
        map.put("count",markList.size());
        map.put("result",markList);
        map.put("msg",ResultEnum.OPERATION_SUCCESS.getMsg());
        return JsonResult.ok(map);
    }

    @ApiOperation(value = "标注地点详情",response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌",required = true),
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true),
            @ApiImplicitParam(name = "markId", value = "标注Id", required = true)})
    @RequestMapping(value = "/markDetail",method = RequestMethod.POST)
    public JsonResult markDetail(@RequestHeader("token")String token,
                                 @RequestParam("userId")int userId,
                                 @RequestParam("markId")int markId){
        ResultEnum resultEnum = userUtil.checkToken(userId, token);
        if(resultEnum!=null){
            return JsonResult.error(resultEnum);
        }
        MapMarkMessage detailMessage = markService.getDetailMessage(userId, markId);
        Map<String,Object> map=new HashMap<>(16);
        map.put("markDetail",detailMessage);
        map.put("msg",ResultEnum.OPERATION_SUCCESS.getMsg());
        return JsonResult.ok(map);
    }

    @ApiOperation(value = "校园推广流",response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌",required = true),
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true)})
    @RequestMapping(value = "/popularMsg",method = RequestMethod.GET)
    public JsonResult popularMsg(@RequestHeader("token")String token,
                                 @RequestParam("userId")int userId){
        ResultEnum resultEnum = userUtil.checkToken(userId, token);
        if(resultEnum!=null){
            return JsonResult.error(resultEnum);
        }
        int successStatus=1;
        List<MapMarkMessage> popularList = markService.findListByStatus(successStatus);
        Map<String,Object> map=new HashMap<>(16);
        map.put("count",popularList.size());
        map.put("result",popularList);
        map.put("msg",ResultEnum.OPERATION_SUCCESS.getMsg());
        return JsonResult.ok(map);
    }

    @ApiOperation(value = "发布校园推广",response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌",required = true),
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true),
            @ApiImplicitParam(name = "markId", value = "标注Id", required = true)})
    @RequestMapping(value = "/publicPopularMsg",method = RequestMethod.POST)
    public JsonResult publicPopularMsg(@RequestHeader("token")String token,
                                 @RequestParam("userId")int userId,
                                       @RequestParam("markId")int markId) throws LbsServerException{
        ResultEnum resultEnum = userUtil.checkToken(userId, token);
        if(resultEnum!=null){
            return JsonResult.error(resultEnum);
        }
        int waitCheckStatus=3,selectStatus=0;;
        markService.updateMarkStatus(userId, markId, waitCheckStatus,selectStatus);
        return JsonResult.ok(ResultEnum.PUBLIC_SUCCESS.getMsg());
    }

    @ApiOperation(value = "推广审核状态",response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌",required = true),
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true)})
    @RequestMapping(value = "/checkList",method = RequestMethod.GET)
    public JsonResult checkList(@RequestHeader("token")String token,
                                       @RequestParam("userId")int userId){
        ResultEnum resultEnum = userUtil.checkToken(userId, token);
        if(resultEnum!=null){
            return JsonResult.error(resultEnum);
        }
        List<PublicCheckVo> checkList = markService.findCheckList(userId);
        Map<String,Object> map=new HashMap<>(16);
        map.put("count",checkList.size());
        map.put("result",checkList);
        map.put("msg",ResultEnum.OPERATION_SUCCESS.getMsg());
        return JsonResult.ok(map);
    }

    @ApiOperation(value = "推广信息审核列表",response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌",required = true),
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true)})
    @RequestMapping(value = "/pcCheckList",method = RequestMethod.GET)
    public JsonResult pcCheckList(@RequestHeader("token")String token,
                                 @RequestParam("userId")int userId){
        ResultEnum resultEnum = userUtil.checkToken(userId, token);
        if(resultEnum!=null){
            return JsonResult.error(resultEnum);
        }
        int waitCheckStatus=3;
        List<MapMarkMessage> popularList = markService.findListByStatus(waitCheckStatus);
        List<MarkCheckVo> result=new ArrayList<>();
        for (MapMarkMessage m:
             popularList) {
            MarkCheckVo mcv=new MarkCheckVo();
            BeanUtils.copyProperties(m, mcv);
            result.add(mcv);
        }
        Map<String,Object> map=new HashMap<>(16);
        map.put("status", HttpStatus.OK.value());
        map.put("count",popularList.size());
        map.put("result",result);
        map.put("msg",ResultEnum.OPERATION_SUCCESS.getMsg());
        return JsonResult.ok(map);
    }

    @ApiOperation(value = "审核功能",response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌",required = true),
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true),
            @ApiImplicitParam(name = "markId", value = "标注Id", required = true),
            @ApiImplicitParam(name = "status", value = "审核状态", required = true)})
    @RequestMapping(value = "/markCheck",method = RequestMethod.POST)
    public JsonResult markCheck(@RequestHeader("token")String token,
                                  @RequestParam("userId")int userId,
                                @RequestParam("markId")int markId,
                                @RequestParam("status")int status) throws LbsServerException {
        ResultEnum resultEnum = userUtil.checkToken(userId, token);
        if (resultEnum != null) {
            return JsonResult.error(resultEnum);
        }
        int selectStatus=3;
        markService.updateMarkStatus(userId, markId, status, selectStatus);
        return JsonResult.ok(ResultEnum.OPERATION_SUCCESS.getMsg());
    }

    @ApiOperation(value = "标注地点管理列表",response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token令牌",required = true),
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true),
            @ApiImplicitParam(name = "selectedUserId", value = "被查看用户id", required = true)})
    @RequestMapping(value = "/findMarkList",method = RequestMethod.POST)
    public JsonResult findMarkList(@RequestHeader("token")String token,
                                         @RequestParam("userId")int userId,
                                         @RequestParam("selectedUserId")int selectedUserId){
        ResultEnum resultEnum = userUtil.checkToken(userId, token);
        if(resultEnum!=null){
            return JsonResult.error(resultEnum);
        }
        List<MarkVo> result = markService.findListByUserId(selectedUserId);
        Map<String,Object> map =new HashMap<>(16);
        map.put("status", HttpStatus.OK.value());
        map.put("count",result.size());
        map.put("msg",ResultEnum.OPERATION_SUCCESS.getMsg());
        map.put("result",result);
        return JsonResult.ok(map);
    }
}
