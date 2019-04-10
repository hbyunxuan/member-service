package com.kykj.tesla.member.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: merchant-service
 * @description:
 * @author: Hp
 * @date: 2018-09-07
 **/
@Configuration
public class MybatisConfig {
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverName;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.maxActive}")
    private Integer maxActive;
    @Value("${spring.datasource.minIdle}")
    private Integer minIdle;
    @Value("${spring.datasource.initialSize}")
    private Integer initSize;
    @Value("${spring.datasource.maxWait}")
    private Integer maxWait;

    @Bean
    public DruidDataSource dataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setDriverClassName(driverName);
        druidDataSource.setUrl(url);
        //初始化大小
        druidDataSource.setInitialSize(initSize);
        //最大
        druidDataSource.setMaxActive(maxActive);
        //最小
        druidDataSource.setMinIdle(minIdle);
        //等待时间
        druidDataSource.setMaxWait(maxWait);
        return druidDataSource;
    }
}
