package com.m2n.bookshelf.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.m2n.bookshelf.util.ConsoleUtil;

import javax.sql.DataSource;
import java.sql.SQLException;


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

    @Bean
    public SpringLiquibase springLiquibase(DataSource dataSource) throws SQLException {
        SpringLiquibase liquibase = new SpringLiquibase();
        // we want to drop the datasbe if it was created before to have immutable version
        liquibase.setDataSource(dataSource);
        // the classpath reference for your liquibase changlog
        liquibase.setChangeLog(changeLog);
        return liquibase;
    }
    
}
