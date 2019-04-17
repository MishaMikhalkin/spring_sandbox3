package com.m2n.bookshelf.util;

import com.m2n.bookshelf.domain.Author;
import com.m2n.bookshelf.domain.Book;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;


import com.m2n.bookshelf.domain.Genre;

@Component
public class ConsoleUtil {

    private final String TWO_COLUMN_DIVIDER = " ----- --------------------------------\n";
    private final String TWO_COLUMN_FORMAT = "| %1$3s | %2$30s |\n";

    public String printTwoHeaderName() {
        return String.format(TWO_COLUMN_FORMAT, "id", "name") + TWO_COLUMN_DIVIDER;
    }

    public String printShellObject(Genre genre) {
    	return String.format(TWO_COLUMN_FORMAT, genre.getId(), genre.getName());
    }

    public String printShellObject(Genre genre, boolean printHeader) {
        String header = printHeader ? printTwoHeaderName() : "";
        return header + printShellObject(genre);
    }


    public String printShellObject(Author author) {
        return String.format("| %1$3d | %2$30s |\n", author.getId(), author.getName());
    }

    public String printShellObject(Author author, boolean printHeader) {
        String header = printHeader ? printTwoHeaderName() : "";
        return header + printShellObject(author);
    }

    private final String FIVE_COLUMN_DIVIDER = " ----- -------------------------------------------------- ------ -------------------- -------------------- \n";
    private final String FIVE_COLUMN_FORMAT = "| %1$3s | %2$48s | %3$4s | %4$18s | %5$18s |\n";

    public String printFiveHeaderName() {
        return String.format(FIVE_COLUMN_FORMAT, "id", "name", "year", "genre", "author") + FIVE_COLUMN_DIVIDER;
    }

    public String printShellObject(Book book) {
        return String.format(FIVE_COLUMN_FORMAT,
                book.getId(), book.getName(), book.getYearOfCreated(), book.getGenre(), book.getAuthorName());
    }

    public String printShellObject(Book book, boolean printHeader) {
        return (printHeader ? printFiveHeaderName() : "") + printShellObject(book);
    }
}
