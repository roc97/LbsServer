package com.roc.mapper;


import com.roc.pojo.UserAttention;

import java.util.List;

/**
 * @author p
 */
public interface UserAttentionMapper{

    int insertPojo(UserAttention userAttention);

    List<UserAttention> getListByUserId(int userId);//获取关注列表

    List<UserAttention> getListByAttentionId(int userAttentionId);//获取关注了我的列表

    int deletePojo(int userId,int userAttentionId);
}
