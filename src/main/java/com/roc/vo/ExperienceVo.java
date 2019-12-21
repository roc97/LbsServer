package com.roc.vo;

import lombok.Data;

/**
 * @author Roc
 * @version 1.0
 * @date 2019/10/30 22:37
 * @description 心得交流视图对象
 */
@Data
public class ExperienceVo {

    private int commId;
    /**
     * 用户id
     */
    private int userId;
    /**
     * 标题
     */
    private String title;
    /**
     * 配图
     */
    private String image;
    /**
     * 内容
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
    /**
     * 姓名
     */
    private String name;
    /**
     * 头像
     */
    private String headImage;

    /**
     * 不加进数据库
     * 操作状态 0该用户点了赞，1该用户点了踩
     */
    private String operateStatus;
    /**
     * 回复总数
     */
    private int replyCount;
}
