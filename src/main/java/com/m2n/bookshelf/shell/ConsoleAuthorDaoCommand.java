package com.m2n.bookshelf.shell;

import com.m2n.bookshelf.domain.Author;
import com.m2n.bookshelf.repository.AuthorRepository;
import com.m2n.bookshelf.util.ConsoleUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@ShellComponent
public class ConsoleAuthorDaoCommand {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleAuthorDaoCommand.class);


    private final AuthorRepository authorDao;
    private final ConsoleUtil consoleUtil;


    @Autowired
    public ConsoleAuthorDaoCommand(AuthorRepository authorDao, ConsoleUtil consoleUtil) {
        this.authorDao = authorDao;
        this.consoleUtil = consoleUtil;
    }

    @ShellMethod("get All Authors")
    public String getAllAuthors() {
    	List<Author> authors = authorDao.findAll();
    	StringBuilder result = new StringBuilder(consoleUtil.printTwoHeaderName());
    	authors.forEach(g -> result.append(consoleUtil.printShellObject(g)));
        return result.toString();
    }

    @ShellMethod("count all authors")
    public String countAllAuthors() {
        return "Number of genres: " + authorDao.count();
    }


    @ShellMethod("insert author")
    public String createAuthor(String name) {
        return consoleUtil.printShellObject(authorDao.save(new Author(UUID.randomUUID().toString(), name)), true);
    }

    @ShellMethod("get author by id")
    public String getAuthorById(String id) {
        return consoleUtil.printShellObject(authorDao.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Could not find entity author with name:" + id)) , true);
    }
    
    @ShellMethod("get author by name")
    public String getAuthorByName(String name) {
        return consoleUtil.printShellObject(authorDao.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Could not find entity author with name:" + name)), true);
    }
    
    @ShellMethod("delete author")
    public void deleteAuthor(String id) {
        Author author = authorDao.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Could not find entity author with name:" + id));
        authorDao.delete(author);
    }
}
