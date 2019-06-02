package com.m2n.bookshelf.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "authors")
@Data
@NoArgsConstructor
public class Author {

	@Id
	private String id;

	private String name;

	public 	Author(String id, String name) {
		this.id = id;
		this.name = name;
	}

	private Set<Book> books;
}

