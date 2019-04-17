package com.m2n.bookshelf.domain;

import lombok.Data;

@Data
public class Book {
	
	private final int id;
	private final String name;
	private final int yearOfCreated;
	private final String genre;
	private final String authorName;

}
