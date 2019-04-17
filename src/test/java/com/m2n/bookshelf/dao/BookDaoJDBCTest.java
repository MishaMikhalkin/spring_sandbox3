package com.m2n.bookshelf.dao;

import com.m2n.bookshelf.Lab52Application;
import com.m2n.bookshelf.domain.Book;
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
class BookDaoJDBCTest {
    @Autowired
    public DataSource dataSource;

    @Autowired
    private BookDaoJDBC em;


    @Test
    void insert() {
        Book Book = new Book(0, "Кентервильское привидение" , 1887, "роман", "Оскар Уайлд");
        Book fromDb = em.insert(Book);
        assertThat(fromDb.getId()).isNotZero();
        assertThat(fromDb.getAuthorName()).isEqualTo(Book.getAuthorName());
        assertThat(fromDb.getGenre()).isEqualTo(Book.getGenre());
        assertThat(fromDb.getName()).isEqualTo(Book.getName());
        assertThat(fromDb.getYearOfCreated()).isEqualTo(Book.getYearOfCreated());

    }

    @Test
    public void findById() {
        Book Book = em.getById(1);
        assertThat(Book.getId()).isEqualTo(1);
        assertThat(Book.getYearOfCreated()).isEqualTo(1965);
    }

    @Test
    public void findAll() {
        List<Book> all = em.getAll();
        Book zero = em.getById(1);
        assertThat(all).containsOnlyOnce(zero);
    }

    @Test
    public void testDelete() {
        int numGenresBeforeOperation = em.count();
        Book forDelete = new Book(0, "Кентервильское привидение" , 1887, "роман", "Оскар Уайлд");
        em.deleteById(forDelete.getId());
        assertThat(em.count()).isEqualTo(numGenresBeforeOperation);
    }
}