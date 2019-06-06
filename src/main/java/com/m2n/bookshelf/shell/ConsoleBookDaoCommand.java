package com.m2n.bookshelf.shell;

import com.m2n.bookshelf.domain.Author;
import com.m2n.bookshelf.domain.Book;
import com.m2n.bookshelf.repository.BookRepository;
import com.m2n.bookshelf.util.ConsoleUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@ShellComponent
@Slf4j
@RequiredArgsConstructor
public class ConsoleBookDaoCommand {

    private final BookRepository bookDao;
    private final ConsoleUtil consoleUtil;

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
                bookDao.save(new Book(UUID.randomUUID().toString(), name, year, genre,
                        new Author(UUID.randomUUID().toString(), authorname))),
                true);
    }

    @ShellMethod("get book")
    public String getBook(String id) {
        return consoleUtil.printShellObject(bookDao
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("there is not book with id:" + id)), true);
    }

    
    @ShellMethod("delete book")
    public void deleteBook(Book book) {
        bookDao.delete(book);
    }
}
