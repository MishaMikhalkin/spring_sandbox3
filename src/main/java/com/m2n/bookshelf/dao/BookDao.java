package com.m2n.bookshelf.dao;

import com.m2n.bookshelf.domain.Book;

import java.util.List;

public interface BookDao {
        Book insert(Book book);

        Book getById(int id);

        Book getByName(String name);

        List<Book> getAll();

        void delete(Book book);
}
