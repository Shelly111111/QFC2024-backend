package com.qunar.qfc2024.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * @author zhangge
 * @date 2024/3/18
 */
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    /**
     * @param resources org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
     * @throws Exception
     * @author zhangge
     * @date 2024/3/18
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                //资源ID
                .resourceId("admin")
                //使用远程服务验证令牌服务
                .tokenServices(tokenServices())
                //无状态模式
                .stateless(true);
    }

    /**
     * @param http org.springframework.security.config.annotation.web.builders.HttpSecurity
     * @throws Exception
     * @author zhangge
     * @date 2024/3/18
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()//校验请求
                //路径匹配规则
                .antMatchers("/**")
                //需要匹配scope
                .access("#oauth2.hasScope('all')")
                //关闭cors和csrf跨域检查
                .and().cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     * 配置access_token远程验证策略
     *
     * @return
     * @author zhangge
     * @date 2024/3/18
     */
    public ResourceServerTokenServices tokenServices() {
        RemoteTokenServices services = new RemoteTokenServices();
        services.setCheckTokenEndpointUrl("http://localhost:8081/oauth/check_token");
        services.setClientId("client_1");
        services.setClientSecret("secret");
        return services;
    }
}
