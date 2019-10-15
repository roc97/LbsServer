package com.roc.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Experience {

    private int experienceId;

    private int userId;//用户id

    private String title;//标题

    private String content;//内容

    private int likeNum;//点赞数

    private int unlikeNum;//踩数

    private Timestamp createTime;
}
