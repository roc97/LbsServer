package com.roc.pojo;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户实体
 * @author p
 */
@Data
public class SysUser implements UserDetails, Serializable {


    private static final long serialVersionUID = -1009735448529946605L;
    private int userId;
    /**
     * 账号
     */
    private String userName;
    /**
     * 昵称
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 性别
     */
    private String sex;
    /**
     * 头像url
     */
    private String headImage;

    private Timestamp  createTime;

    private List<SysRole> roles;

    /**
     * 后续设计
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (SysRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {//账号是否过期
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {//账号是否冻结
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {//证书是否过期
        return true;
    }

    @Override
    public boolean isEnabled() { //是否可用
        return true;
    }
}
