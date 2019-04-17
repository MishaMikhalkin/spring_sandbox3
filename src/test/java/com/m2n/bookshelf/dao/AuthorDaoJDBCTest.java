package com.m2n.bookshelf.dao;

import com.m2n.bookshelf.Lab52Application;
import com.m2n.bookshelf.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Lab52Application.class})
@ActiveProfiles("test")
class AuthorDaoJDBCTest {
    @Autowired
    public DataSource dataSource;

    @Autowired
    private AuthorDaoJDBC em;


    @Test
    void insert() {
        Author author = new Author(0, "1");
        Author fromDb = em.insert(author);
        assertThat(fromDb.getId()).isNotZero();
        assertThat(fromDb.getName()).isEqualTo(author.getName());
    }

    @Test
    public void findById() {
        Author author = em.getById(1);
        assertThat(author.getId()).isEqualTo(1);
        assertThat(author.getName()).isEqualTo("Братья Стругацкие");
    }

    @Test
    public void findByName() {
        Author author = em.getByName("Оскар Уайлд");
        assertThat(author.getId()).isEqualTo(2);
        assertThat(author.getName()).isEqualTo("Оскар Уайлд");
    }

    @Test
    public void findAll() {
        List<Author> all = em.getAll();
        Author zero = em.getById(1);
        assertThat(all).containsOnlyOnce(zero);
    }

    @Test
    public void testDelete() {
        int numGenresBeforeOperation = em.count();
        Author forDelete = em.insert(new Author(0, "forDelete"));
        em.deleteById(forDelete.getId());
        assertThat(em.count()).isEqualTo(numGenresBeforeOperation);
    }
}