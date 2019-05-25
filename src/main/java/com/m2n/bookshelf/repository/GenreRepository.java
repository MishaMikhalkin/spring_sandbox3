package com.m2n.bookshelf.repository;

import com.m2n.bookshelf.domain.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface  GenreRepository extends CrudRepository<Genre, Integer> {


    @Override
    public Genre save(Genre entity);

    @Override
    public Optional<Genre> findById(Integer integer);

    Optional<Genre> findByName(String name);

    @Override
    public boolean existsById(Integer integer);

    @Override
    public List<Genre> findAll();

    public List<Genre> findAllById(List<Integer> integers);

    @Override
    public long count();

    @Override
    public void deleteById(Integer integer);

    @Override
    public void delete(Genre entity);

    @Override
    public void deleteAll();
}
