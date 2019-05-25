package com.m2n.bookshelf.repository;

import com.m2n.bookshelf.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Integer> {

    Book save(Book entity);

    Optional<Book> findById(Integer id);

    Optional<Book> findByName(String name);

    boolean existsById(Integer id);

    List<Book> findAll();

    List<Book> findAllById(Iterable<Integer> ids);

    long count();

    void deleteById(Integer id);

    void delete(Book entity);
}
