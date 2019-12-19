package com.roc.service;

import com.roc.exception.LbsServerException;
import com.roc.mapper.MapMarkMessageMapper;
import com.roc.mapper.SysUserMapper;
import com.roc.pojo.MapMarkMessage;
import com.roc.pojo.SysUser;
import com.roc.utils.DateUtil;
import com.roc.utils.ResultEnum;
import com.roc.vo.MarkCheckVo;
import com.roc.vo.MarkVo;
import com.roc.vo.PublicCheckVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Roc
 * @version 1.0
 * @date 2019/10/29 20:24
 * @description 标注地点和推广相关的业务接口
 */
@Service
public class MarkService {

    @Autowired
    private MapMarkMessageMapper messageMapper;
    @Autowired
    private SysUserMapper userMapper;

    public Map<String, Object> findMarkList(){
        List<MarkVo> markList = messageMapper.getAllList();
        Map<String, Object> map = new HashMap<>(16);
        map.put("count", markList.size());
        map.put("result", markList);
        map.put("msg", ResultEnum.OPERATION_SUCCESS.getMsg());
        return map;
    }

    public Map<String, Object> getDetailMessage(int userId,int markId){
        Map<String, Object> map = new HashMap<>(16);
        MapMarkMessage detailMessage = messageMapper.getDetailMessage(markId,userId);
        if (detailMessage==null){
            throw new LbsServerException(ResultEnum.OPERATION_FAILURE);
        }
        if(detailMessage.getUserId()!=userId){
            detailMessage.setSelfStatus(false);
        }else {
            detailMessage.setSelfStatus(true);
        }
        map.put("markDetail", detailMessage);
        map.put("msg", ResultEnum.OPERATION_SUCCESS.getMsg());
        return map;
    }

    public void publicMessage(double lng,double lat,int userId,String title,String content){
        MapMarkMessage message=new MapMarkMessage();
        //未推广
        message.setStatus(0);
        message.setLng(lng);
        message.setLat(lat);
        message.setUserId(userId);
        message.setTitle(title);
        message.setContent(content);
        int i = messageMapper.insertPojo(message);
        if (i!=1){
            throw new LbsServerException(ResultEnum.PUBLIC_FAILURE);
        }
    }

    public Map<String,Object> getMarkCheckData(){
        Map<String,Object> map=new HashMap<>(16);
        int waitCheckStatus=3;
        List<MapMarkMessage> popularList = messageMapper.getListByStatus(waitCheckStatus);
        List<MarkCheckVo> result=new ArrayList<>();
        for (MapMarkMessage m:
                popularList) {
            MarkCheckVo mcv=new MarkCheckVo();
            BeanUtils.copyProperties(m, mcv);
            result.add(mcv);
        }
        map.put("status", HttpStatus.OK.value());
        map.put("count",popularList.size());
        map.put("result",result);
        map.put("msg",ResultEnum.OPERATION_SUCCESS.getMsg());
        return map;
    }

    public void updateMarkStatus(int markId,int status,int selectStatus){
        MapMarkMessage message = messageMapper.getByMarkId(markId, selectStatus);
        if (message==null){
            throw new LbsServerException(ResultEnum.OPERATION_FAILURE);
        }
        int i = messageMapper.updatePojo(markId, status);
        if (i!=1){
            throw new LbsServerException(ResultEnum.PUBLIC_FAILURE);
        }
    }

    public Map<String, Object> findCheckList(int userId){
        int status=0;
        Map<String, Object> map = new HashMap<>(16);
        List<PublicCheckVo> checkList = messageMapper.getCheckList(userId, status);
        map.put("count", checkList.size());
        map.put("result", checkList);
        map.put("msg", ResultEnum.OPERATION_SUCCESS.getMsg());
        return map;
    }

    public Map<String, Object> findMarkAdminData(int userId){
        Map<String, Object> map = new HashMap<>(16);
        SysUser user = userMapper.getPojo(userId);
        if(user==null){
            throw new LbsServerException(ResultEnum.OPERATION_FAILURE);
        }
        List<MarkVo> result = messageMapper.getListByUserId(userId);
        map.put("status", HttpStatus.OK.value());
        map.put("count", result.size());
        map.put("msg", ResultEnum.OPERATION_SUCCESS.getMsg());
        map.put("result", result);
        return map;
    }

    public Map<String, Object> schoolPublish(){
        int successStatus = 1;
        List<MapMarkMessage> popularList = messageMapper.getListByStatus(successStatus);
        Map<String, Object> map = new HashMap<>(16);
        map.put("count", popularList.size());
        map.put("result", popularList);
        map.put("msg", ResultEnum.OPERATION_SUCCESS.getMsg());
        return map;
    }

}
