package com.qunar.qfc2024.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * 令牌存储策略
 *
 * @author zhangge
 * @date 2024/3/18
 */
@Configuration
public class TokenConfig {
    @Bean
    public TokenStore tokenStore() {
        //使用基于内存的普通令牌
        return new InMemoryTokenStore();
    }
}
