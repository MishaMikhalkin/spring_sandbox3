package com.m2n.bookshelf.dao;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.distribution.Version;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.data.mongodb.repository.MongoRepository;

public class MongoTest {

    private static MongodExecutable mongodExecutable;
    private static MongodProcess mongod;
    public static MongoClient mongo;

    static void setup() throws Exception {



        IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION).build();

        MongodStarter runtime = MongodStarter.getDefaultInstance();

        MongodExecutable mongodExecutable = null;
        MongodProcess mongod = null;
        mongodExecutable = runtime.prepare(mongodConfig);
        mongod = mongodExecutable.start();

        MongoClient mongo = new MongoClient(new ServerAddress(mongodConfig.net().getServerAddress(), mongodConfig.net().getPort()));

    }


    static void shutdown() {
        mongod.stop();
        mongodExecutable.stop();
    }
}
