package com.m2n.bookshelf.domain;

import lombok.Data;

import java.util.List;

@Data
public class Author {


	private final int id;
	private final String firstName;
	private final String lastName;
	private final List<Book> books;

}
