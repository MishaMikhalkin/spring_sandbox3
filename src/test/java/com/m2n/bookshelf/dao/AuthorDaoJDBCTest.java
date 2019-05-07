package com.m2n.bookshelf.dao;

import com.m2n.bookshelf.Lab52Application;
import com.m2n.bookshelf.domain.Author;
import com.m2n.bookshelf.domain.Genre;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Import(AuthorDaoJPA.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class AuthorDaoJDBCTest {

    @Autowired
    private AuthorDaoJPA em;


    @Test
    void insert() {
        Author author = new Author( "1");
        Author fromDb = em.insert(author);
        assertThat(fromDb.getId()).isNotZero();
        assertThat(fromDb.getName()).isEqualTo(author.getName());
    }

    @Test
    public void testDelete() {
        int numBeforOperation = em.getAll().size();
        Author forDelete = em.insert(new Author("forDelete"));
        em.delete(forDelete);
        assertThat(em.getAll().size()).isEqualTo(numBeforOperation);
    }

    @Test
    public void findById() {
        Author author = em.getById(1);
        assertThat(author.getId()).isEqualTo(1);
        assertThat(author.getName()).isEqualTo("Братья Стругацкие");
    }
}