package com.qunar.qfc2024.web.config;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MybatisPage配置类
 *
 * @author zhangge
 * @date 2024/6/27
 **/
@EnableTransactionManagement
@Configuration
@MapperScan("com.qunar.qfc2024.infrastructure.mapper")
public class MybatisPageConfig {

    /**
     * mybatis-plus分页插件
     *
     * @return com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //Dbtype.数据库 可改成自己对应的数据库，当前为MySQL。
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}

