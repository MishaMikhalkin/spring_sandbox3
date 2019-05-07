package com.m2n.bookshelf.dao;


import com.m2n.bookshelf.domain.Genre;
import liquibase.database.core.H2Database;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Import(GenreDaoJPA.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class GenreDaoJPATest {

    @Autowired
    private GenreDaoJPA em;

    @Test
    public void testCountAndInsert() {
        Genre genre = new Genre("0");
        Genre fromDB = em.insert(genre);
        assertThat(fromDB.getId()).isNotZero();
        assertThat(fromDB.getName()).isEqualTo(genre.getName());
    }

    @Test
    public void testDelete() {
        int numGenresBeforeOperation = em.getAll().size();
        Genre forDelete = em.insert(new Genre("forDelete"));
        em.delete(forDelete);
        assertThat(em.getAll().size()).isEqualTo(numGenresBeforeOperation);
    }

    @Test
    public void findById() {
        Genre genre = em.getById(1);
        assertThat(genre.getId()).isEqualTo(1);
        assertThat(genre.getName()).isEqualTo("роман");
    }
}