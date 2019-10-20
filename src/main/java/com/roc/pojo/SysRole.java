package com.roc.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 角色实体
 * @author Administrator
 */
@Data
public class SysRole implements Serializable {

    private int roleId;

    /**
     *  英文角色名称
     */
    private String roleName;

    /**
     * 中文角色名称
     */
    private String roleNameZh;

    private Timestamp createTime;
}
