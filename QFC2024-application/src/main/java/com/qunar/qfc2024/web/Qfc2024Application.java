package com.qunar.qfc2024.web;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author zhangge
 *
 */
@Slf4j
@SpringBootApplication(
        scanBasePackages = {
                "com.qunar.qfc2024"
        },
        excludeName = {"org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"}
)
@EnableResourceServer
@MapperScan("com.qunar.qfc2024.infrastructure.mapper")
public class Qfc2024Application {

    public static void main(String[] args) {
        SpringApplication.run(Qfc2024Application.class, args);
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        //24小时
        Long maxAge= 86400L;
        corsConfiguration.setMaxAge(maxAge);
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }

}
