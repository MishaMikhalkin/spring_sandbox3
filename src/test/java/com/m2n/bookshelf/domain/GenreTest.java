package com.m2n.bookshelf.domain;


import com.m2n.bookshelf.Lab52Application;
import com.m2n.bookshelf.dao.GenreDaoJDBC;
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
public class GenreTest {
    @Autowired
    public DataSource dataSource;


    @Autowired
    private GenreDaoJDBC em;

    @Test
    public void testCountAndInsertGenre() {
        Genre genre = new Genre(0, "0");
        Genre fromDb = em.insert(genre);
        assertThat(fromDb.getId()).isNotZero();
        assertThat(fromDb.getName()).isEqualTo(genre.getName());
    }

    @Test
    public void testDeleteGenre() {
        int numGenresBeforeOperation = em.count();
        Genre forDelete = em.insert(new Genre(0, "forDelete"));
        em.deleteById(forDelete.getId());
        assertThat(em.count()).isEqualTo(numGenresBeforeOperation);
    }


    @Test
    public void findById() {
        Genre genre = em.getById(1);
        assertThat(genre.getId()).isEqualTo(1);
        assertThat(genre.getName()).isEqualTo("роман");
    }

    @Test
    public void findAll() {
        List<Genre> all = em.getAll();
        Genre zero = em.getById(1);
        assertThat(all).containsOnlyOnce(zero);
    }
}