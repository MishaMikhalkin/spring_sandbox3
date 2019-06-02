package com.m2n.bookshelf.config;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.distribution.Version;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
@ComponentScan(basePackages = "com.m2n.bookshelf")
@EnableMongoRepositories({ "com.m2n.bookshelf.repository" })
public class MongoTestConfig {
    IMongodConfig mongodConfig;
    MongodExecutable mongod;


    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION).build();
        MongodStarter runtime = MongodStarter.getDefaultInstance();
        MongodExecutable mongodExecutable = null;
        MongodProcess mongod = null;
        mongodExecutable = runtime.prepare(mongodConfig);
        mongod = mongodExecutable.start();
        MongoClient mongoClient = new MongoClient(new ServerAddress(mongodConfig.net().getServerAddress(), mongodConfig.net().getPort()));
        return new SimpleMongoDbFactory(mongoClient, "bookshelf");

    }

    public void destroy() throws Exception {
        mongod.stop();
    }
}
