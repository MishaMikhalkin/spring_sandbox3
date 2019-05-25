package com.m2n.bookshelf.shell;

import com.m2n.bookshelf.domain.Author;
import com.m2n.bookshelf.domain.Book;
import com.m2n.bookshelf.domain.Genre;
import com.m2n.bookshelf.repository.BookRepository;
import com.m2n.bookshelf.util.ConsoleUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@ShellComponent
public class ConsoleBookDaoCommand {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleBookDaoCommand.class);


    private final BookRepository bookDao;
    private final ConsoleUtil consoleUtil;


    @Autowired
    public ConsoleBookDaoCommand(BookRepository bookDao, ConsoleUtil consoleUtil) {
        this.bookDao = bookDao;
        this.consoleUtil = consoleUtil;
    }

    @ShellMethod("get All Books")
    public String getAllBooks() {
    	List<Book> books = bookDao.findAll();
    	StringBuilder result = new StringBuilder(consoleUtil.printFiveHeaderName());
    	books.forEach(g -> result.append(consoleUtil.printShellObject(g)));
        return result.toString();
    }

    @ShellMethod("count all books")
    public String countAllBooks() {
        return "Number of genres: " + bookDao.count();
    }


    @ShellMethod("insert book")
    public String createBook(String name, int year, String genre, String authorname) {
        return consoleUtil.printShellObject(
                bookDao.save(new Book(name, year, new Genre(genre), new Author(authorname))),
                true);
    }

    @ShellMethod("get book")
    public String getBook(int id) {
        return consoleUtil.printShellObject(bookDao
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("there is not book with id:" + id)), true);
    }

    
    @ShellMethod("delete book")
    public void deleteBook(Book book) {
        bookDao.delete(book);
    }
}
