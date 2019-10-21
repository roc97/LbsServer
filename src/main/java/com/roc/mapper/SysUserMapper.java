package com.roc.mapper;

import com.roc.pojo.SysRole;
import com.roc.pojo.SysUser;

import java.util.List;

/**
 * @author p
 */
public interface SysUserMapper extends BaseMapper<SysUser>{

    SysUser getByUserName(String userName);

    List<SysRole> getRolesByUserId(int userId);
}
