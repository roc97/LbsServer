package com.roc.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户角色实体
 * @author p
 */
@Data
public class SysRoleUser implements Serializable {

    private int id;
    /**
     * 用户id
     */
    private int  userId;
    /**
     * 角色id
     */
    private int roleId;

    private Timestamp createTime;
}
