package com.m2n.bookshelf.repository;

import com.m2n.bookshelf.domain.Author;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Integer> {

    Author save(Author entity);

    Optional<Author> findById(Integer id);

    Optional<Author> findByName(String name);

    boolean existsById(Integer id);

    List<Author> findAll();

    List<Author> findAllById(Iterable<Integer> ids);

    long count();

    void deleteById(Integer id);

    void delete(Author entity);

    void deleteAll();
}
