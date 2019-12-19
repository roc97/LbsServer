package com.roc.service;

import com.roc.exception.LbsServerException;
import com.roc.mapper.ReplyMapper;
import com.roc.pojo.Reply;
import com.roc.utils.ResultEnum;
import com.roc.vo.ReplyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Roc
 * @version 1.0
 * @date 2019/11/3 11:34
 * @description 回复心得业务层
 */
@Service
public class ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    public void replyExperience(int userId,int commId,String content){
        Reply reply = new Reply();
        reply.setContent(content);
        reply.setUserId(userId);
        reply.setExperienceId(commId);
        int i = replyMapper.insertPojo(reply);
        if (i != 1) {
            throw new LbsServerException(ResultEnum.REPLY_FAILURE);
        }
    }
}
