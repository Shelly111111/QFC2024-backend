package com.qunar.qfc2024.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author zhangge
 * @date 2024/3/18
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private TokenStore tokenStore;
    /**
     * 会通过之前的ClientDetailsServiceConfigurer注入到Spring容器中
     */
    @Autowired
    private ClientDetailsService clientDetailsService;

    /**
     * 配置客户端详情服务
     *
     * @param clients org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
     * @throws Exception
     * @author zhangge
     * @date 2024/3/18
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // client 密码
        //String finalSecret = "{bcrypt}" + new BCryptPasswordEncoder().encode("123456");
        //用于password认证
        clients.inMemory()
                //密码模式
                //客户端ID
                .withClient("client_1")
                //客户端拥有的资源列表
                .resourceIds("admin")
                //认证范围，"authorization_code"、"password"、"client_credentials"、"implicit"、"refresh_token"
                .authorizedGrantTypes("password", "refresh_token")
                //允许授权的范围
                .scopes("all")
//                .autoApprove(false)//跳转到授权页面
//                .authorities("admin")
                .secret("secret");
    }

    /**
     * 令牌管理服务
     *
     * @return org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices
     * @author zhangge
     * @date 2024/3/18
     */
    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices services = new DefaultTokenServices();
        //客户端详情服务
        services.setClientDetailsService(clientDetailsService);
        //允许令牌自动刷新
        services.setSupportRefreshToken(true);
        //令牌存储策略
        services.setTokenStore(tokenStore);
        //令牌默认有效期2小时
        services.setRefreshTokenValiditySeconds(7200);
        //刷新令牌默认有效期3天
        services.setRefreshTokenValiditySeconds(259200);
        return services;
    }

    /**
     * 配置令牌访问端点和令牌服务
     *
     * @author zhangge
     * @date 2024/3/18
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                //用户管理
                .userDetailsService(userDetailsService)
                //启用oauth2管理
                .authenticationManager(authenticationManager)
                //令牌管理服务
                .tokenServices(tokenServices())
                //接收GET和POST
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE);
    }

    /**
     * 配置令牌端点申请令牌的安全约束
     *
     * @param oauthServer org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
     * @author zhangge
     * @date 2024/3/18
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        //oauth/check_token公开
        oauthServer.checkTokenAccess("permitAll()")
                .tokenKeyAccess("permitAll()")
                //表单认证，申请令牌
                .allowFormAuthenticationForClients();
    }

    /**
     * 设置授权码模式的授权码如何存取
     *
     * @return org.springframework.security.oauth2.provider.code.AuthorizationCodeServices
     * @author zhangge
     * @date 2024/3/18
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();
    }
}
