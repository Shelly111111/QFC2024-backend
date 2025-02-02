package com.qunar.qfc2024.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     *
     * @param http org.springframework.security.config.annotation.web.builders.HttpSecurity
     * @throws Exception
     *
     * @author zhangge
     * @date 2023/4/19
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/**")
                .hasAnyAuthority("admin")//这里采用了注解的方法级权限配置
                .anyRequest().authenticated();
                //.anyRequest().permitAll();
    }
}
