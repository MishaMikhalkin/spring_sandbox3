package com.m2n.bookshelf.domain;


import com.m2n.bookshelf.Lab52Application;
//import com.m2n.bookshelf.config.H2TestProfileJPAConfig;
import com.m2n.bookshelf.dao.GenreDaoJDBC;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import com.ninja_squad.dbsetup.operation.Operation;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;

import javax.sql.DataSource;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.operation.CompositeOperation.sequenceOf;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Lab52Application.class})
@ActiveProfiles("test")
public class GenreTest {
    @Autowired
    public DataSource dataSource;

    @Before
    public void prepare() {
        Operation operation =
                sequenceOf(
                        insertInto("genres")
                                .columns( "NAME")
                                .values( "value")
                                .build());
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
        dbSetup.launch();
    }

    @Autowired
    private GenreDaoJDBC em;

    @Test
    public void insertGenre() {
        Genre genre = new Genre(0, "0");
        Genre fromDb = em.insert(genre);
        assertThat(fromDb.getId()).isNotZero();
        assertThat(fromDb.getName()).isEqualTo(genre.getName());
    }

}