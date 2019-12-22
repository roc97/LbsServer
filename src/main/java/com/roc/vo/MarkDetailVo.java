package com.roc.vo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Roc
 * @version 1.0
 * @date 2019/11/3 10:01
 * @description 标注地点详细信息视图
 */
@Data
public class MarkDetailVo {

    private int markId;
    /**
     * 用户id
     */
    private int userId;
    /**
     * 纬度
     */
    private double lat;
    /**
     * 经度
     */
    private double lng;
    /**
     * 头像
     */
    private String headImage;

    private String name;

    private String title;

    private String content;

    private Timestamp createTime;
}
