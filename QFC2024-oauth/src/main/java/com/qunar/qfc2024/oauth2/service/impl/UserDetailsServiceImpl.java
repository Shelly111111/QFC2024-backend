package com.qunar.qfc2024.oauth2.service.impl;

import com.qunar.qfc2024.oauth2.po.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 实现用户授权服务
 *
 * @author: 漫舞枪神
 * @date: 2023/4/18
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserServiceImpl userService;

    /**
     * 重载通过用户名加载用户方法，通过用户名查找数据库并获取密码返回用户权限信息
     * @param username 用户名 String
     * @return org.springframework.security.core.userdetails.UserDetails
     * @throws UsernameNotFoundException
     *
     * @author zhangge
     * @date 2023/4/18
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPO user = userService.findByUsername(username);
        return org.springframework.security.core.userdetails.User.withUsername(username).password(user.getPassword()).authorities("admin").build();
    }
}
