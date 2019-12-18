package com.roc.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Roc
 * @version 1.0
 * @date 2019/12/18 9:12
 * @description  点赞（踩）表
 */
@Data
public class PraiseOrDisagree implements Serializable {

    private int id;

    /**
     * 操作对象，一般是用户id
     */
    private int operateObject;

    /**
     * 操作目标,一般是心得id或者回复id
     */
    private int operateTarget;

    /**
     * 操作类型,0点赞，1踩
     */
    private int operateType;

    /**
     * 目标类型 0心得，1回复
     */
    private int targetType;

    private Timestamp createTime;
}
