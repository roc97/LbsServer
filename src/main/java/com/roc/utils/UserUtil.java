package com.roc.utils;

import com.roc.pojo.SysUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Roc
 * @version 1.0
 * @date 2019/10/22 22:19
 */
public class UserUtil {
    public static SysUser getCurrentUser(){
        return (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
