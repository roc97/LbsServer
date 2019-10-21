package com.roc.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**权限实体
 * @author Administrator
 */
@Data
public class SysPermission implements Serializable {

    private int permissionId;
    /**
     * 权限名称
     */
    private String permissionName;
    /**
     * 权限描述
     */
    private String description;
    /**
     * 路径
     */
    private String url;
    /**
     * 排序id
     */
    private int orderIndex;
    /**
     * 父节点id
     */
    private int parentId;

    private List<SysRole> roles;

    private Timestamp createTime;
}
