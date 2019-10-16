package com.roc.service;

import com.roc.mapper.SysPermissionMapper;
import com.roc.pojo.SysPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Transactional(readOnly = true)
    public List<SysPermission> getAllPermission(){
        return sysPermissionMapper.getAllPermission();
    }

    public SysPermission getPermission(int id){
        return sysPermissionMapper.getPojo(id);
    }

    public int deletePermission(int id){
        return sysPermissionMapper.deletePojo(id);
    }

    public int updatePermission(SysPermission permission){
        return sysPermissionMapper.updatePojo(permission);
    }
    @Transactional(readOnly = true)
    public List<SysPermission> getChildrenIds(int parentId){
        return sysPermissionMapper.getChildrenIds(parentId);
    }
}
