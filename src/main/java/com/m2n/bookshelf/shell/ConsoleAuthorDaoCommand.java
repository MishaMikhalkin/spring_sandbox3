package com.m2n.bookshelf.shell;

import com.m2n.bookshelf.dao.AuthorDao;
import com.m2n.bookshelf.domain.Author;
import com.m2n.bookshelf.util.ConsoleUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class ConsoleAuthorDaoCommand {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleAuthorDaoCommand.class);


    private final AuthorDao authorDao;
    private final ConsoleUtil consoleUtil;


    @Autowired
    public ConsoleAuthorDaoCommand(AuthorDao authorDao, ConsoleUtil consoleUtil) {
        this.authorDao = authorDao;
        this.consoleUtil = consoleUtil;
    }

    @ShellMethod("get All Authors")
    public String getAllAuthors() {
    	List<Author> authors = authorDao.getAll();
    	StringBuilder result = new StringBuilder(consoleUtil.printTwoHeaderName());
    	authors.stream().forEach(g -> result.append(consoleUtil.printShellObject(g)));
        return result.toString();
    }

    @ShellMethod("count all authors")
    public String countAllAuthors() {
        return "Number of genres: " + authorDao.count();
    }


    @ShellMethod("insert author")
    public String createAuthor(String name) {
        return consoleUtil.printShellObject(authorDao.insert(new Author(0, name)), true);
    }

    @ShellMethod("get author by id")
    public String getAuthorById(int id) {
        return consoleUtil.printShellObject(authorDao.getById(id), true);
    }
    
    @ShellMethod("get author by name")
    public String getAuthorByName(String name) {
        return consoleUtil.printShellObject(authorDao.getByName(name), true);
    }
    
    @ShellMethod("delete author")
    public void deleteAuthor(int id) {
        authorDao.deleteById(id);
    }
}
