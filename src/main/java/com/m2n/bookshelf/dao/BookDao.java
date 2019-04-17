package com.m2n.bookshelf.dao;

import com.m2n.bookshelf.domain.Book;

import java.util.List;

public interface BookDao {
        int count();

        Book insert(Book book);

        Book getById(int id);

        List<Book> getAll();

        void deleteById(int id);
}
