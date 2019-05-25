package com.m2n.bookshelf.dao;

import com.m2n.bookshelf.domain.Author;
import com.m2n.bookshelf.domain.Book;
import com.m2n.bookshelf.domain.Genre;
import com.m2n.bookshelf.repository.AuthorRepository;
import com.m2n.bookshelf.repository.BookRepository;
import com.m2n.bookshelf.repository.GenreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
//@ActiveProfiles("test")
//@Import({BookRepository.class, AuthorRepository.class, GenreRepository.class})
@DataJpaTest
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class BookDaoJDBCTest {

    @Autowired
    private BookRepository em;

    @Autowired
    private AuthorRepository authorDaoJPA;

    @Autowired
    private GenreRepository genreDaoJPA;



    @Test
    void insert() {
        Genre genre = genreDaoJPA.findByName("роман").orElseThrow(() -> new EntityNotFoundException());
        Author author = authorDaoJPA.findByName("Оскар Уайлд").orElseThrow(() -> new EntityNotFoundException());
        Book book = new Book("Кентервильское привидение" , 1887, genre, author);

        Book fromDb = em.save(book);
        assertThat(fromDb.getId()).isNotZero();
        assertThat(fromDb.getName()).isEqualTo(book.getName());
        assertThat(fromDb.getYearOfCreated()).isEqualTo(book.getYearOfCreated());

        assertThat(fromDb.getGenre().getId()).isNotZero();
        assertThat(fromDb.getGenre().getName()).isEqualTo(book.getGenre().getName());

        assertThat(fromDb.getAuthorName().getId()).isNotZero();
        assertThat(fromDb.getAuthorName().getName()).isEqualTo(book.getAuthorName().getName());

    }

    @Test
    public void findById() {
        Book Book = em.findById(1).orElseThrow(() -> new EntityNotFoundException());
        assertThat(Book.getId()).isEqualTo(1);
        assertThat(Book.getYearOfCreated()).isEqualTo(1965);
    }

    @Test
    public void findAll() {
        List<Book> all = em.findAll();
        assertThat(all.size()).isEqualTo(2);

        Book element = all.get(0);
        assertThat(element.getName()).isEqualTo("Понедельник начинается в субботу");
        assertThat(element.getId()).isEqualTo(1);
        assertThat(element.getYearOfCreated()).isEqualTo(1965);
        assertThat(element.getAuthorName()).isNotNull();
        assertThat(element.getAuthorName().getName()).isEqualTo("Братья Стругацкие");
        assertThat(element.getGenre().getName()).isEqualTo("научная фантастика");
    }

    @Test()

    public void testDelete() {
        Genre genre = genreDaoJPA.findByName("роман").orElseThrow(() -> new EntityNotFoundException());
        Author author = authorDaoJPA.findByName("Оскар Уайлд").orElseThrow(() -> new EntityNotFoundException());
        Book forDelete = new Book("Кентервильское привидение" , 1887, genre, author);
        em.delete(forDelete);


        try {
            Book ret = em.findById(forDelete.getId()).orElseThrow(() -> new EntityNotFoundException(""));
        } catch (EntityNotFoundException e) {
            assertThat(true);
        }

        List<Book> books = em.findAll();
        assertThat(books).doesNotContain(forDelete);
    }
}