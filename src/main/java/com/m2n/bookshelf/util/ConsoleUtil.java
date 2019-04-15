package com.m2n.bookshelf.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;


import com.m2n.bookshelf.domain.Genre;

@Component
public class ConsoleUtil {

    private final String GENRE_DIVIDER = " ----- --------------------------------\n";

    public String printGenreName() {
        return String.format("| %1$3s | %2$30s |\n", "id", "name") + GENRE_DIVIDER;
    }

	
    public String printShellObject(Genre genre) {
    	return String.format("| %1$3d | %2$30s |\n", genre.getId(), genre.getName());
    }
}
