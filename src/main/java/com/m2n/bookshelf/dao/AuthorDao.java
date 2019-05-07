package com.m2n.bookshelf.dao;

import com.m2n.bookshelf.domain.Author;

import java.util.List;

public interface AuthorDao {
    Author insert(Author book);

    Author getById(int id);

    Author getByName(String name);

    List<Author> getAll();

    void delete(Author author);
}
