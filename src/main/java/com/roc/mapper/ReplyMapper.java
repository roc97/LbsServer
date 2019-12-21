package com.roc.mapper;

import com.roc.pojo.Reply;
import com.roc.vo.ReplyVo;

import java.util.List;

/**
 * @author p
 */
public interface ReplyMapper{

    int insertPojo(Reply reply);

    /**
     * 根据心得id获取所有主回复
     * @param experienceId 心得id
     * @param operateObject 查看回复者的id
     * @return
     */
    List<ReplyVo> getByExperienceId(int experienceId,int operateObject);

    List<Reply> getByMainReplyId(int mainReplyId);//根据主回复，获取相应的二级回复

    /**
     * 更新回复的点赞(踩)
     * @param replyId
     * @param type 0点赞 1踩
     * @param total
     * @return
     */
    int updateLikeNumAndUnlikeNum(int replyId,int type,int total);

    int deleteMainOrGeneralReply(int replyId,int userId);//删除主回复

    int cascadeDelete(int mainReplyId);//主回复被删,级联删除关联主回复的

    /**
     * 删除心得相关的回复
     * @param experienceId
     * @return
     */
    int deleteByExperienceId(int experienceId);

}
