package com.roc.mapper;


import com.roc.pojo.UserAttention;
import com.roc.vo.ExperienceVo;
import com.roc.vo.MarkDetailVo;

import java.util.List;

/**
 * @author p
 */
public interface UserAttentionMapper{

    UserAttention getPojo(int userId,int userAttentionId);

    int insertPojo(UserAttention userAttention);

    /**
     * 获取关注列表的标点详细数据
     * @param userId
     * @return
     */
    List<MarkDetailVo> getMarkListByUserId(int userId);


    /**
     * 获取关注列表的心得交流数据
     * @param userId
     * @return
     */
    List<ExperienceVo> getCommListByUserId(int userId);

    /**
     * 获取关注了我的列表
     * @param userAttentionId
     * @return
     */
    List<UserAttention> getListByAttentionId(int userAttentionId);

    int deletePojo(int userId,int userAttentionId);
}
