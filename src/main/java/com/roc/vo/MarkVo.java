package com.roc.vo;

import lombok.Data;

/**
 * @author Roc
 * @version 1.0
 * @date 2019/10/29 20:36
 * @description 标记点视图对象
 */
@Data
public class MarkVo {
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

    private String address;
}
