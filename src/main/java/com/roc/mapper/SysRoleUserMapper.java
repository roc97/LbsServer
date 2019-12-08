package com.roc.mapper;

import com.roc.pojo.SysRoleUser;

import java.util.List;

/**
 * @author p
 */
public interface SysRoleUserMapper {

    int insertPojo(int userId,int roleId);

    SysRoleUser getPojo(int userId,int roleId);

    List<SysRoleUser> getByUserId(int userId);//获取相应用户的角色

    List<SysRoleUser> getByRoleId(int roleId);//获取该角色的所有用户

    int deletePojo(int userId,int roleId);

}
