package com.m2n.bookshelf.dao;

import com.m2n.bookshelf.domain.Author;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
public class AuthorDaoJPA implements AuthorDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Author insert(Author author) {
        entityManager.persist(author);
        entityManager.flush();
        return author;
    }

    @Override
    public Author getById(int id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    public Author getByName(String name) {
        TypedQuery<Author> query = entityManager.createQuery("select e from Author e where e.name = :name", Author.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = entityManager.createQuery("select g from Author g", Author.class);
        return query.getResultList();
    }

    @Override
    public void delete(Author author) {
        entityManager.remove(author);
    }
}
