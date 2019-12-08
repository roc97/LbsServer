package com.roc.service;

import com.roc.exception.LbsServerException;
import com.roc.mapper.MapMarkMessageMapper;
import com.roc.mapper.SysUserMapper;
import com.roc.pojo.MapMarkMessage;
import com.roc.pojo.SysUser;
import com.roc.utils.DateUtil;
import com.roc.utils.ResultEnum;
import com.roc.vo.MarkVo;
import com.roc.vo.PublicCheckVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<MarkVo> findMarkList(){
        return messageMapper.getAllList();
    }

    public MapMarkMessage getDetailMessage(int userId,int markId)throws LbsServerException{
        MapMarkMessage detailMessage = messageMapper.getDetailMessage(markId);
        if (detailMessage==null){
            throw new LbsServerException(ResultEnum.OPERATION_FAILURE);
        }
        if(detailMessage.getUserId()!=userId){
            detailMessage.setSelfStatus(false);
        }else {
            detailMessage.setSelfStatus(true);
        }
        return detailMessage;
    }

    public int publicMessage(MapMarkMessage message){
       return messageMapper.insertPojo(message);
    }

    public List<MapMarkMessage> findListByStatus(int status){
        return messageMapper.getListByStatus(status);
    }

    public void updateMarkStatus(int userId,int markId,int status,int selectStatus)throws LbsServerException{
        MapMarkMessage message = messageMapper.getByUserIdAndMarkId(userId, markId, selectStatus);
        if (message==null){
            throw new LbsServerException(ResultEnum.OPERATION_FAILURE);
        }
        int i = messageMapper.updatePojo(markId, status);
        if (i!=1){
            throw new LbsServerException(ResultEnum.PUBLIC_FAILURE);
        }
    }

    public List<PublicCheckVo> findCheckList(int userId){
        int status=0;
        return messageMapper.getCheckList(userId,status);
    }

    public List<MarkVo> findListByUserId(int userId)throws LbsServerException{
        SysUser user = userMapper.getPojo(userId);
        if(user==null){
            throw new LbsServerException(ResultEnum.OPERATION_FAILURE);
        }
        return messageMapper.getListByUserId(userId);
    }

    public void deleteMark(int markId) throws LbsServerException{
        int i = messageMapper.deletePojo(markId);
        if (i!=1){
            throw new LbsServerException(ResultEnum.PUBLIC_FAILURE);
        }
    }

    public List<Map> countTimeSlotRecords(int status,String startTime,String endTime){
        return messageMapper.countTimeSlotRecords(status, DateUtil.stampToDate(startTime), DateUtil.stampToDate(endTime));
    }
}
