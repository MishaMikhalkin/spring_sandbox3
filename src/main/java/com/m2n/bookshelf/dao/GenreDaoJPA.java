package com.m2n.bookshelf.dao;

import com.m2n.bookshelf.domain.Genre;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
public class GenreDaoJPA implements GenreDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Genre insert(Genre genre) {
        entityManager.persist(genre);
        entityManager.flush();
        return genre;
    }

    @Override
    public Genre getById(int id) {
        return entityManager.find(Genre.class, id);
    }

    @Override
    public Genre getByName(String name) {
        TypedQuery<Genre> query = entityManager.createQuery("select e from Genre e where e.name = :name", Genre.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query = entityManager.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public void delete(Genre genre) {
        entityManager.remove(genre);
    }
}
