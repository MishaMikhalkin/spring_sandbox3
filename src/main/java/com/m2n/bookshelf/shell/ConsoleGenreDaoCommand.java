package com.m2n.bookshelf.shell;

import java.util.List;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.m2n.bookshelf.dao.GenreDao;
import com.m2n.bookshelf.domain.Genre;
import com.m2n.bookshelf.util.ConsoleUtil;

@ShellComponent
public class ConsoleGenreDaoCommand {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleGenreDaoCommand.class);


    private final GenreDao genreDao;
    private final ConsoleUtil consoleUtil;


    @Autowired
    public ConsoleGenreDaoCommand(GenreDao genreDao, ConsoleUtil consoleUtil) {
        this.genreDao = genreDao;
        this.consoleUtil = consoleUtil;
    }

    @ShellMethod("get All Genres")
    public String getAllGenres() {
    	List<Genre> genres = genreDao.getAll();
    	StringBuilder result = new StringBuilder(consoleUtil.printGenreName());
    	genres.stream().forEach( g -> result.append(consoleUtil.printShellObject(g)));
        return result.toString();
    }

    @ShellMethod("count all genres")
    public String countAllGenres() {
        return "Number of genres: " + genreDao.count();
    }


    @ShellMethod("insert genre")
    public String createGenre(String name) {
        String result = consoleUtil.printGenreName();
        result = result + consoleUtil.printShellObject(genreDao.insert(new Genre(0, name)));
        return result;
    }

    @ShellMethod("get genre")
    public String getGenre(int id) {
        String result = consoleUtil.printGenreName();
        result = result + consoleUtil.printShellObject(genreDao.getById(id));
        return result;
    }

    @ShellMethod("delete genre")
    public void deleteGenre(int id) {
        genreDao.deleteById(id);
    }
}
