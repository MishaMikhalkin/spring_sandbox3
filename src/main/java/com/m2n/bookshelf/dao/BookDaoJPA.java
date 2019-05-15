package com.m2n.bookshelf.dao;

import com.m2n.bookshelf.domain.Author;
import com.m2n.bookshelf.domain.Book;
import com.m2n.bookshelf.domain.Genre;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
public class BookDaoJPA implements BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    @PersistenceContext
    private EntityManager genreManager;


    @Transactional
    @Override
    public Book insert(Book book) {
        Genre genre = book.getGenre();
        genre = genreManager.find(Genre.class, genre.getId());
        if (genre == null) {
            genreManager.persist(genre);
        }

        Author author = book.getAuthorName();
        author = genreManager.find(Author.class, author.getId());
        if (author == null) {
            genreManager.persist(author);
        }

        entityManager.persist(book);
        entityManager.flush();
        return book;
    }

    @Override
    public Book getById(int id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    public Book getByName(String name) {
        TypedQuery<Book> query = entityManager.createQuery("select e from Book e where e.name = :name", Book.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = entityManager.createQuery("select g from Book g join fetch g.genre ", Book.class);
        return query.getResultList();
    }

    @Override
    public void delete(Book book) {
        entityManager.remove(book);
    }

    @Override
    public long count() {
        TypedQuery<Long> query = entityManager.createQuery("select count(a) from Book a", Long.class);
        return query.getSingleResult();
    }

}
