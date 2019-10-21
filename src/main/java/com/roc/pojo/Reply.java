package com.roc.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 回复实体
 * @author p
 */
@Data
public class Reply implements Serializable {

    private int replyId;
    /**
     * 用户id
     */
    private int userId;
    /**
     * 心得id
     */
    private int experienceId;
    /**
     * 回复内容
     */
    private String content;
    /**
     * 点赞数
     */
    private int likeNum;
    /**
     * 踩数
     */
    private int unlikeNum;

    private Timestamp createTime;
    /**
     * 主回复id 默认为0,即主回复
     */
    private int mainReplyId;
    /**
     * 上级用户id 默认为0，即这条记录是主回复
     */
    private int parentUserId;


    /* 以下字段不存库 */
    /**
     *  用户名
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
    /**
     * 上级用户昵称
     */
    private String parentUserName;
}
