package com.roc.mapper;

import com.roc.pojo.SysPermission;

import java.util.List;

public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    List<SysPermission> getChildrenIds(int parentId);
}
