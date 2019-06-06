package com.m2n.bookshelf.util;

import com.m2n.bookshelf.domain.Author;
import com.m2n.bookshelf.domain.Book;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.*;


@Component

public class ConsoleUtil {

    private final String TWO_COLUMN_FORMAT = "| %1$36s | %2$30s |\n";
    private final String FIVE_COLUMN_FORMAT = "| %1$36s | %2$48s | %3$4s | %4$18s | %5$18s |\n";

    private String createDivider(String val) {
        return val.concat(StringUtils.repeat("-", val.length()));
    }

    public String printFiveHeaderName() {
        return createDivider(String.format(FIVE_COLUMN_FORMAT, "id", "name", "year", "genre", "author"));
    }


    public String printTwoHeaderName() {
        return createDivider(String.format(TWO_COLUMN_FORMAT, "id", "name"));
    }


    public String printShellObject(Author author) {
        return String.format(TWO_COLUMN_FORMAT, author.getId(), author.getName());
    }

    public String printShellObject(Author author, boolean printHeader) {
        String header = printHeader ? printTwoHeaderName() : "";
        return header + printShellObject(author);
    }

    public String printShellObject(Book book) {
        return String.format(FIVE_COLUMN_FORMAT,
                book.getId(), book.getName(), book.getYearOfCreated(), book.getGenre(), book.getAuthorName().getName());
    }

    public String printShellObject(Book book, boolean printHeader) {
        return (printHeader ? printFiveHeaderName() : "") + printShellObject(book);
    }
}
