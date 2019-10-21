package com.roc.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 关注实体
 * @author p
 */
@Data
public class UserAttention implements Serializable {

    private int attentionId;
    /**
     * 用户id
     */
    private int userId;
    /**
     * 用户关注对象的id
     */
    private int userAttentionId;

    private Timestamp createTime;

    /* 以下字段不存库 */
    /**
     * 用户名
     */
    private String userName;
    /**
     * 性别
     */
    private String sex;
    /**
     * 头像url
     */
    private String headImage;
}
