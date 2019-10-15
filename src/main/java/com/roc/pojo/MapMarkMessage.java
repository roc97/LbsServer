package com.roc.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 地图标注信息实体
 */
@Data
public class MapMarkMessage {

    private int markId;

    private int userId;//用户id

    private double lat;//纬度

    private double lng;//经度

    private String title;//标题

    private String content;//内容

    private int status;//发布推广状态 0未申请推广 1审核通过 2未通过 3待审核

    private Timestamp createTime;

    //以下字段不插入数据库
    private String sex;
    private String userName;
}
