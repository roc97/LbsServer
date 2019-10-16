package com.roc.config;

import com.roc.pojo.SysPermission;
import com.roc.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Component
public class CustomMetaDataSource  implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private SysPermissionService sysPermissionService;
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        List<SysPermission> allPermission = sysPermissionService.getAllPermission();
        for (SysPermission p:
             allPermission) {
//            if (antPathMatcher.match(p.getUrl(),requestUrl)
//            && p.get)
            //待修改实体类和实体类xml等
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
