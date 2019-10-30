package com.roc.service;

import com.roc.exception.LbsServerException;
import com.roc.mapper.MapMarkMessageMapper;
import com.roc.pojo.MapMarkMessage;
import com.roc.utils.ResultEnum;
import com.roc.vo.MarkVo;
import com.roc.vo.PublicCheckVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public int publicPopularMsg(int userId,int markId,int status)throws LbsServerException{
        int unPublicStatus=0;
        MapMarkMessage message = messageMapper.getByUserIdAndMarkId(userId, markId, unPublicStatus);
        if (message==null){
            throw new LbsServerException(ResultEnum.OPERATION_FAILURE);
        }
        return messageMapper.updatePojo(markId,status);
    }

    public List<PublicCheckVo> findCheckList(int userId){
        int status=0;
        return messageMapper.getCheckList(userId,status);
    }
}
