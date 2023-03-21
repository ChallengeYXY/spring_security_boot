package com.yangxinyu.app.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @BelongsProject : spring_security_boot
 * @BelongsPackage : com.yangxinyu.app.entity
 * @Date : 2023/3/21 14:11
 * @Author : 星宇
 * @Description :
 */
@Data
@Builder
public class User implements UserDetails {
    /**
     * 封装自己的用户
     */
    private Student student;

    /**
     * 权限列表
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /**
     * 账户
     * 如果当前容器中存在密码编码器，就不需要指定解码方式了！
     * @return
     */
    @Override
    public String getPassword() {
        return student.getPassword();
    }

    /**
     * 密码
     * @return
     */
    @Override
    public String getUsername() {
        return student.getUsername();
    }

    /**
     * 是否将用户不上锁
     * false为上锁
     * true为不上锁
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    /**
     * 用户是否为不失效
     * false为失效
     * true为不失效
     * @return
     */

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * 账户是否不过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 设置用户凭证已过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
