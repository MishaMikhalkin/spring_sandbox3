package com.m2n.bookshelf.dao;

import com.m2n.bookshelf.config.MongoTestConfig;
import com.m2n.bookshelf.domain.Author;
import com.m2n.bookshelf.repository.AuthorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.NoSuchElementException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@DataMongoTest(excludeAutoConfiguration = {MongoTestConfig.class})
@ExtendWith(SpringExtension.class)
@DisplayName("Test Author repository")
class AuthorDaoJDBCTest {

    @Autowired
    private AuthorRepository em;






    @Test
    @DisplayName(value =  "save and findById should pass")
    void insert() {
        Author author = new Author(UUID.randomUUID().toString(), "1");
        Author fromDb = em.save(author);
        assertThat(fromDb.getName()).isEqualTo(author.getName());
        try {
            Author authorFind = em.findById("0").orElseThrow(() -> new NoSuchElementException(""));
        } catch (NoSuchElementException e) {
            assertThat(true);
        }
        Author athorFind2 = em.findById(author.getId()).orElseThrow(() -> new NoSuchElementException(""));
        assertThat(fromDb).isEqualTo(athorFind2);

    }

    @Test
    @DisplayName(value = "delete and count should pass")
    public void testDelete() {
        long numBeforOperation = em.count();
        Author forDelete = em.save(new Author(UUID.randomUUID().toString(),  "forDelete"));
        em.delete(forDelete);
        assertThat(em.count()).isEqualTo(numBeforOperation);
    }
}