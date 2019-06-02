package com.m2n.bookshelf.repository;

import com.m2n.bookshelf.domain.Book;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableMongoRepositories(basePackages = "com.m2nd.bookshelf")
public interface BookRepository extends CrudRepository<Book, String> {

    Book save(Book entity);

    Optional<Book> findById(String id);

    Optional<Book> findByName(String name);

    boolean existsById(String id);

    List<Book> findAll();

    List<Book> findAllById(Iterable<String> ids);

    long count();

    void deleteById(String id);

    void delete(Book entity);
}
