package com.m2n.bookshelf.dao;

import java.util.List;

import com.m2n.bookshelf.domain.Genre;


public interface GenreDao {

    int count();

    Genre insert(Genre genre);

    Genre getById(int id);
    
    List<Genre> getAll();

    void deleteById(int id);
}
