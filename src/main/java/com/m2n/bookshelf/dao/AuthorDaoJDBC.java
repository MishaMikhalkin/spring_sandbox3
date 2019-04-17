package com.m2n.bookshelf.dao;

import com.m2n.bookshelf.domain.Author;
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
public class AuthorDaoJDBC implements AuthorDao {

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorDaoJDBC(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.jdbc = this.namedParameterJdbcOperations.getJdbcOperations();
    }

    @Override
    public int count() {
        return jdbc.queryForObject("select count(*) from authors", Integer.class);
    }

    @Override
    public Author insert(Author author) {
        Map<String, String> paramMap = Map.of("name", author.getName());
        SqlParameterSource params = new MapSqlParameterSource(paramMap);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcOperations.update ("insert into authors (`name`) " +
                        "values (:name)", params,
                keyHolder,new String[]{"id"});

        return new Author(keyHolder.getKey().intValue(), author.getName());
    }

    @Override
    public Author getById(int id) {
        Map<String, Integer> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select id, `name` from authors where id = :id", params, new AuthorMapper()
        );
    }

    @Override
    public Author getByName(String name) {
        Map<String, String> params = Map.of("name", name);
        return namedParameterJdbcOperations.queryForObject(
                "select id, `name` from authors where `name` = :name", params, new AuthorMapper()
        );
    }

    @Override
    public List<Author> getAll() {
        return namedParameterJdbcOperations.query("select * from authors", new AuthorMapper());
    }

    @Override
    public void deleteById(int id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update("delete from authors where id = :id", params);
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            return new Author(id, name);
        }
    }
}
