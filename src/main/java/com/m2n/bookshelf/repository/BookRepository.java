package com.m2n.bookshelf.repository;

import com.m2n.bookshelf.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    Optional<Book> findById(String id);

    Optional<Book> findByName(String name);

}
