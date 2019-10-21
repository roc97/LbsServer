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

    private Timestamp createTime;
}
