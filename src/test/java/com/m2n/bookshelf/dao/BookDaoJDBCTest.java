package com.m2n.bookshelf.dao;

import com.m2n.bookshelf.config.MongoTestConfig;
import com.m2n.bookshelf.domain.Author;
import com.m2n.bookshelf.domain.Book;
import com.m2n.bookshelf.repository.AuthorRepository;
import com.m2n.bookshelf.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest(excludeAutoConfiguration = {MongoTestConfig.class})
@ExtendWith(SpringExtension.class)
@DisplayName(value = "Test Book Repository")
class BookDaoJDBCTest {

    @Autowired
    private BookRepository em;
    @Autowired
    private AuthorRepository authorDaoJPA;

    @Test
    @DisplayName(value = "save, findAll should pass")
    void insert() {
        Author author = authorDaoJPA.save(new Author(UUID.randomUUID().toString(), "Оскар Уайлд"));
        Book book = new Book(UUID.randomUUID().toString(), "Кентервильское привидение" , 1887, "жанр", author);

        Book fromDb = em.save(book);
        assertThat(fromDb.getId()).isNotEmpty();
        assertThat(fromDb.getName()).isEqualTo(book.getName());
        assertThat(fromDb.getYearOfCreated()).isEqualTo(book.getYearOfCreated());

        assertThat(fromDb.getGenre()).isEqualTo(book.getGenre());

        assertThat(fromDb.getAuthorName().getName()).isEqualTo(book.getAuthorName().getName());

        List<Book> books = em.findAll();
        assertThat(books).contains(fromDb);
    }




    @Test()
    @DisplayName(value = "count, delete, findById should pass")
    public void testDelete() {

        Author author = authorDaoJPA.save(new Author(UUID.randomUUID().toString(), "Оскар Уайлд"));
        Book book = new Book(UUID.randomUUID().toString(), "Кентервильское привидение" , 1887, "жанр", author);
        Book fromDb = em.save(book);
        long count =  em.count();
        assertThat(count).isEqualTo(1);
        em.delete(fromDb);
        try {
            em.findById(fromDb.getId()).orElseThrow(() ->new NoSuchElementException());
        } catch (NoSuchElementException e) {
            assertThat(true);
        }
    }
}