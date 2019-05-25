package com.m2n.bookshelf.shell;

import java.util.List;

import com.m2n.bookshelf.repository.GenreRepository;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.m2n.bookshelf.domain.Genre;
import com.m2n.bookshelf.util.ConsoleUtil;

import javax.persistence.EntityNotFoundException;

@ShellComponent
public class ConsoleGenreDaoCommand {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleGenreDaoCommand.class);


    private final GenreRepository genreDao;
    private final ConsoleUtil consoleUtil;


    @Autowired
    public ConsoleGenreDaoCommand(GenreRepository genreDao, ConsoleUtil consoleUtil) {
        this.genreDao = genreDao;
        this.consoleUtil = consoleUtil;
    }

    @ShellMethod("get All Genres")
    public String getAllGenres() {
    	List<Genre> genres = genreDao.findAll();
    	StringBuilder result = new StringBuilder(consoleUtil.printTwoHeaderName());
    	genres.forEach(g -> result.append(consoleUtil.printShellObject(g)));
        return result.toString();
    }

    @ShellMethod("count all genres")
    public String countAllGenres() {
        return "Number of genres: " + genreDao.count();
    }


    @ShellMethod("insert genre")
    public String createGenre(String name) {
        return consoleUtil.printShellObject(genreDao.save(new Genre(name)), true);
    }

    @ShellMethod("get genre by id")
    public String getGenreById(int id) {
        return consoleUtil.printShellObject(genreDao
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("could not find entity with id:" + id))
                , true);
    }

    @ShellMethod("get genre by name")
    public String getGenreByName(String name) {
        return consoleUtil.printShellObject(genreDao
                .findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("could not find entity with id:" + name))
                , true);
    }

    @ShellMethod("delete genre")
    public void deleteGenre(int id) {
        genreDao.delete(genreDao
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("could not find entity with id:" + id)));
    }
}
