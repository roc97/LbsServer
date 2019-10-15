package com.roc.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 角色权限实体
 */
@Data
public class SysPermissionRole {

    private int id;

    private int roleId;//角色id

    private int permissionId;//权限id

    private Timestamp createTime;
}
