package com.m2n.bookshelf.repository;

import com.m2n.bookshelf.domain.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {

    Author save(Author entity);

    Optional<Author> findById(String id);

    Optional<Author> findByName(String name);

    boolean existsById(Integer id);

    List<Author> findAll();

    List<Author> findAllById(Iterable<String> ids);

    long count();

    void deleteById(String id);

    void delete(Author entity);

    void deleteAll();
}
