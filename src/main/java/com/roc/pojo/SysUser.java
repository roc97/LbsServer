package com.roc.pojo;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户实体
 */
@Data
public class SysUser implements UserDetails {

    private int userId;

    private String userName;//用户昵称

    private String account;//账号

    private String password;//密码

    private String sex;//性别

    private String headImage;//头像url

    private Timestamp  createTime;

    private List<SysRole> roles;
    //后续设计
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
        return this.account;
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
