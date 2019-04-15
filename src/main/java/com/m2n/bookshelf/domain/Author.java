package com.m2n.bookshelf.domain;

import java.util.List;

public class Author {


	private final int id;
	private final String firstName;
	private final String lastName;
	private final List<Book> books;
	
	public Author(int id, String firstName, String lastName, List<Book> books) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.books = books;
	}

	
	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public List<Book> getBooks() {
		return books;
	}	

}
