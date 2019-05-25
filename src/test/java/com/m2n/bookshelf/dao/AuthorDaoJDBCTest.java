package com.m2n.bookshelf.dao;

import com.m2n.bookshelf.Lab52Application;
import com.m2n.bookshelf.domain.Author;
import com.m2n.bookshelf.domain.Genre;
import com.m2n.bookshelf.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.sql.DataSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
//@ActiveProfiles("test")
@DataJpaTest
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class AuthorDaoJDBCTest {

    @Autowired
    private AuthorRepository em;


    @Test
    void insert() {
        Author author = new Author( "1");
        Author fromDb = em.save(author);
        assertThat(fromDb.getId()).isNotZero();
        assertThat(fromDb.getName()).isEqualTo(author.getName());
    }

    @Test
    public void testDelete() {
        long numBeforOperation = em.count();
        Author forDelete = em.save(new Author("forDelete"));
        em.delete(forDelete);
        assertThat(em.count()).isEqualTo(numBeforOperation);
    }

    @Test
    public void findById() {
        Author author = em.findById(1).orElseThrow(() -> new EntityNotFoundException(""));
        assertThat(author.getId()).isEqualTo(1);
        assertThat(author.getName()).isEqualTo("Братья Стругацкие");
    }
}