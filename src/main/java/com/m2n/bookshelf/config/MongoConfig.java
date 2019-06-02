package com.m2n.bookshelf.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.m2n.bookshelf.util.ConsoleUtil;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import javax.sql.DataSource;


@Configuration
public class MongoConfig extends AbstractMongoConfiguration {


    @Bean
    public ConsoleUtil consoleUtil() {
        return  new ConsoleUtil();
    }


    @Value("${spring.mongo.host}")
    private String host;

    @Value("${spring.mongo.port}")
    private int port;

    @Value("${spring.mongo.databaseName}")
    private String databaseName;


    @Override
    public MongoClient mongoClient() {
        return new MongoClient(host, port);
    }

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }


    @Bean
    public MongoDbFactory mongoDbFactory(MongoClient mongoClient) throws Exception {
        return new SimpleMongoDbFactory(mongoClient, getDatabaseName());
    }

}
