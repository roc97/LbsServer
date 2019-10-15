package com.roc.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 关注实体
 */
@Data
public class UserAttention {

    private int attentionId;

    private int userId;//用户id

    private int userAttentionId;//用户关注对象的id

    private Timestamp createTime;

    //以下字段不存库
    private String userName;//用户名
    private String sex;//性别
    private String headImage;//头像url
}
