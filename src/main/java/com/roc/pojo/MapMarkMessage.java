package com.roc.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 地图标注信息实体
 * @author p
 */
@Data
public class MapMarkMessage implements Serializable {

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
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 发布推广状态 0未申请推广 1审核通过 2未通过 3待审核
     */
    private int status;

    private Timestamp createTime;
    /**
     * 以下字段不插入数据库
     */
    private String headImage;
    private String name;
    /**
     * 判断是否关注标记
     */
    private boolean selfStatus;
}
