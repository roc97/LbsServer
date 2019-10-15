package com.roc.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SysPermission {

    private int permissionId;

    private String permissionName;//权限名称

    private String description;//权限描述

    private String url;//路径

    private int orderIndex;//排序id

    private int parentId;//父节点id

    private Timestamp createTime;
}
