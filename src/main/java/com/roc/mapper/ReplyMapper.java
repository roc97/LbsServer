package com.roc.mapper;

import com.roc.pojo.Reply;
import com.roc.vo.ReplyVo;

import java.util.List;

/**
 * @author p
 */
public interface ReplyMapper{

    int insertPojo(Reply reply);

    List<ReplyVo> getByExperienceId(int experienceId);//根据心得id获取所有主回复

    List<Reply> getByMainReplyId(int mainReplyId);//根据主回复，获取相应的二级回复

    int updateLikeNumAndUnlikeNum(int replyId,int likeNum,int unlikeNum);//更新点赞和踩

    int deleteMainOrGeneralReply(int replyId,int userId);//删除主回复

    int cascadeDelete(int mainReplyId);//主回复被删,级联删除关联主回复的

}
