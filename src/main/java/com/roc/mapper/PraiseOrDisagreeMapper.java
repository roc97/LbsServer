package com.roc.mapper;

import com.roc.pojo.PraiseOrDisagree;

/**
 * @author Roc
 * @version 1.0
 * @date 2019/12/18 16:30
 * @description 点赞（踩） mapper接口
 */
public interface PraiseOrDisagreeMapper {

    /**
     * 插入实体
     * @param praiseOrDisagree 对象
     * @return
     */
    int insertPojo(PraiseOrDisagree praiseOrDisagree);

    /**
     * 删除实体
     * @param operateObject 操作对象
     * @param operateTarget 操作目标（心得id或者回复id）
     * @param targetType 目标类型（0心得，1回复）
     * @return
     */
    int deletePojo(int operateObject,int operateTarget,int targetType);
}
