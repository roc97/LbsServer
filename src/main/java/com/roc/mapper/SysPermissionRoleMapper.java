package com.roc.mapper;

import com.roc.pojo.SysPermissionRole;

import java.util.List;

public interface SysPermissionRoleMapper {

    int insertPojo(int roleId,int permissionId);

    SysPermissionRole getPojo(int roleId, int permissionId);

    List<SysPermissionRole> getByRoleId(int roleId);//获取相应角色的所有权限集

    List<SysPermissionRole> getByPermissionId(int permissionId);//拥有该权限的所有角色

    int deletePojo(int roleId,int permissionId);
}
