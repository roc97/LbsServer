package com.roc.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 用户角色实体
 */
@Data
public class SysRoleUser {

    private int id;

    private int  userId;//用户id

    private int roleId;//角色id

    private Timestamp createTime;
}
