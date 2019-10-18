package com.roc.config;

import com.roc.pojo.SysPermission;
import com.roc.pojo.SysRole;
import com.roc.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 通用元数据
 */
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
            if (antPathMatcher.match(p.getUrl(),requestUrl)
            && p.getRoles().size()>0){
                //如果当前的请求匹配了数据中的url
                //则获取他的角色列表
                List<SysRole> roles= p.getRoles();
                int size=roles.size();
                String[] values=new String[size];
                for (int i = 0; i < size; i++) {
                    values[i] = roles.get(i).getRoleName();
                }
                return SecurityConfig.createList(values);
            }

        }
        //没有匹配上的资源，都是登录访问
        return SecurityConfig.createList("ROLE_LOGIN");
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
