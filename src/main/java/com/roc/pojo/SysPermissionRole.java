package com.roc.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 角色权限实体
 * @author p
 */
@Data
public class SysPermissionRole implements Serializable {

    private int id;
    /**
     * 角色id
     */
    private int roleId;
    /**
     * 权限id
     */
    private int permissionId;

    private Timestamp createTime;
}
