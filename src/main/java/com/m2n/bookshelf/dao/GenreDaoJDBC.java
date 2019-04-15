package com.m2n.bookshelf.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import com.m2n.bookshelf.domain.Genre;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class GenreDaoJDBC implements GenreDao {

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public GenreDaoJDBC(NamedParameterJdbcOperations namedParameterJdbcOperations) {
    	this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    	this.jdbc = this.namedParameterJdbcOperations.getJdbcOperations();
    }
	
	@Override
	public int count() {		
		return jdbc.queryForObject("select count(*) from genres", Integer.class);
	}

	@Override
	public Genre insert(Genre genre) {

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("name", genre.getName());
		SqlParameterSource params = new MapSqlParameterSource(paramMap);
		KeyHolder keyHolder = new GeneratedKeyHolder();

		int id = namedParameterJdbcOperations.update ("insert into genres (name) values (:name)",
				params, keyHolder,new String[]{"id"});

		return new Genre(id, genre.getName());
	}



	@Override
	public Genre getById(int id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select * from genres where id = :id", params, new GenreMapper()
        );
		
	}

	@Override
	public List<Genre> getAll() {
    	return namedParameterJdbcOperations.query("select * from genres", new GenreMapper());
	}

	@Override
	public void deleteById(int id) {
    	Map<String, Object> params = Collections.singletonMap("id", id);
    	namedParameterJdbcOperations.update("delete from genres where id = :id", params);
		
	}

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }


}
