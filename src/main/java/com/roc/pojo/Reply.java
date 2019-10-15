package com.roc.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 回复实体
 */
@Data
public class Reply {

    private int replyId;

    private int userId;//用户id

    private int experienceId;//心得id

    private String content;//回复内容

    private int likeNum;//点赞数

    private int unlikeNum;//踩数

    private Timestamp createTime;

    private int mainReplyId;//主回复id 默认为0,即主回复

    private int parentUserId;//上级用户id 默认为0，即这条记录是主回复


    //以下字段不存库
    private String userName;//用户名
    private String sex;//性别
    private String headImage;//头像url
    private String parentUserName;//上级用户昵称
}
