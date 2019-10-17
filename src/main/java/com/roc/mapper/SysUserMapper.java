package com.roc.mapper;

import com.roc.pojo.SysRole;
import com.roc.pojo.SysUser;

import java.util.List;

public interface SysUserMapper extends BaseMapper<SysUser>{

    SysUser getByAccount(String account);

    List<SysRole> getRolesByUserId(int userId);
}
