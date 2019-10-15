package com.roc.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 角色实体
 */
@Data
public class SysRole {

    private int roleId;

    private String roleName;//角色名称

    private Timestamp createTime;
}
