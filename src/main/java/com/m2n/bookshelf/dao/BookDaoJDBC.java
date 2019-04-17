package com.m2n.bookshelf.dao;

import com.m2n.bookshelf.domain.Author;
import com.m2n.bookshelf.domain.Book;
import com.m2n.bookshelf.domain.Genre;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJDBC implements BookDao {

    private final GenreDao genreDao;
    private final AuthorDao authorDao;
    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoJDBC(NamedParameterJdbcOperations namedParameterJdbcOperations, GenreDao genreDao, AuthorDao authorDao) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.jdbc = this.namedParameterJdbcOperations.getJdbcOperations();
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from authors", Integer.class);
    }

    @Override
    public Book insert(Book book) {

        Genre genre = null;
        try {
            genre = genreDao.getByName(book.getGenre());
        } catch (EmptyResultDataAccessException e) {
            genre = new Genre(0, book.getGenre());
            genreDao.insert(genre);
        }

        Author author = null;
        try {
            author = authorDao.getByName(book.getAuthorName());
        } catch (EmptyResultDataAccessException e) {
            author = new Author(0, book.getAuthorName());
            authorDao.insert(author);
        }


        Map<String, String> paramMap = Map.of(
                "name", book.getName(),
                "created", String.valueOf(book.getYearOfCreated()),
                "genre_id", String.valueOf(genre.getId()),
                "author_id", String.valueOf(author.getId())
        );
        SqlParameterSource params = new MapSqlParameterSource(paramMap);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcOperations.update("insert into books (`name`, created, genre_id, author_id) " +
                        "values (:name, :created, :genre_id, :author_id)", params,
                keyHolder, new String[]{"id"});

        return new Book(keyHolder.getKey().intValue(),
                book.getName(),
                book.getYearOfCreated(),
                book.getGenre(),
                book.getAuthorName());
    }

    @Override
    public Book getById(int id) {
        Map<String, Integer> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select books.id as book_id, " +
                        "books.`name` as book_name, " +
                        "created, " +
                        "genres.name as genre_name, " +
                        "authors.name as author_name " +
                        "from books " +
                        "join genres on books.id = genres.id " +
                        "join authors on books.id = authors.id" +
                        " where books.id = :id", params, new BookMapper()
        );
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query("select books.id as book_id, books.`name` as book_name, created, " +
                "genres.name as genre_name, authors.name as author_name from books " +
                "left join genres on books.id = genres.id " +
                "left join authors on books.id = authors.id", new BookMapper());
    }

    @Override
    public void deleteById(int id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update("delete from books where id = :id", params);
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("book_id");
            String name = resultSet.getString("book_name");
            int yearOfCreated = resultSet.getInt("created");
            String genre = resultSet.getString("genre_name");
            String authorName = resultSet.getString("author_name");
            return new Book(id, name, yearOfCreated, genre, authorName);
        }
    }
}
