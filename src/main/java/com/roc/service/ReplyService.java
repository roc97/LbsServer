package com.roc.service;

import com.roc.mapper.ReplyMapper;
import com.roc.pojo.Reply;
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

    public int replyExperience(Reply reply){
        return replyMapper.insertPojo(reply);
    }

    public List<ReplyVo> findListByCommId(int commId){
        return replyMapper.getByExperienceId(commId);
    }

}
