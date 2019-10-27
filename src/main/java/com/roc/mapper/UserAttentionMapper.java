package com.roc.mapper;


import com.roc.pojo.UserAttention;

import java.util.List;

/**
 * @author p
 */
public interface UserAttentionMapper{

    UserAttention getPojo(int userId,int userAttentionId);

    int insertPojo(UserAttention userAttention);

    /**
     * 获取关注列表
     * @param userId
     * @return
     */
    List<UserAttention> getListByUserId(int userId);

    /**
     * 获取关注了我的列表
     * @param userAttentionId
     * @return
     */
    List<UserAttention> getListByAttentionId(int userAttentionId);

    int deletePojo(int userId,int userAttentionId);
}
