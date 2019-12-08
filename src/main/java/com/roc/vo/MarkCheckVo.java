package com.roc.vo;

import lombok.Data;

/**
 * @author Roc
 * @version 1.0
 * @date 2019/12/8 16:05
 * @description 推广流审核Vo
 */
@Data
public class MarkCheckVo {

    private int markId;
    /**
     * 用户id
     */
    private int userId;

    private int status;
    /**
     * 头像
     */
    private String headImage;

    private String name;

    private String title;

    private String content;
}
