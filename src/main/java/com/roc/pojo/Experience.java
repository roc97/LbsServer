package com.roc.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**心得实体
 * @author p
 */
@Data
public class Experience implements Serializable {

    private int experienceId;
    /**
     * 用户id
     */
    private int userId;
    /**
     * 标题
     */
    private String title;
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
     * 配图
     */
    private String image;

    /**
     * 不加进数据库
     * 操作状态 0该用户点了赞，1该用户点了踩
     */
    private String operateStatus;

    private Timestamp createTime;
}
