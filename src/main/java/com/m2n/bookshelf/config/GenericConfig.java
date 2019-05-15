package com.m2n.bookshelf.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.m2n.bookshelf.util.ConsoleUtil;

import javax.sql.DataSource;


@Configuration
public class GenericConfig {


    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.driver-class-name}")
    private String driverName;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.liquibase.change-log}")
    private String changeLog;

    @Bean
    public ConsoleUtil consoleUtil() {
        return  new ConsoleUtil();
    }


    @Bean
    DataSource dataSource() {
        return DataSourceBuilder.create()
                .url(url)
                .driverClassName(driverName)
                .username(username)
                .password(password)
                .build();
    }
    
}
