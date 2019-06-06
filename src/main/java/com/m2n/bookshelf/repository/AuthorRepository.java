package com.m2n.bookshelf.repository;

import com.m2n.bookshelf.domain.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {


    Optional<Author> findById(String id);

    Optional<Author> findByName(String name);

}
