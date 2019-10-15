package com.roc.mapper;

import com.roc.pojo.SysUser;

public interface SysUserMapper extends BaseMapper<SysUser>{

    SysUser getByAccount(String account);
}
