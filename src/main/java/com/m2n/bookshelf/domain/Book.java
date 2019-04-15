package com.m2n.bookshelf.domain;


public class Book {
	
	private final int id;
	private final String name;
	private final int yearOfCreated;
	private final String genre;
	private final Book author;

	public Book(int id, String name, int yearOfCreated, String genre, Book author) {
		super();
		this.id = id;
		this.name = name;
		this.yearOfCreated = yearOfCreated;
		this.genre = genre;
		this.author = author;
	}
	
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getYearOfCreated() {
		return yearOfCreated;
	}

	public String getGenre() {
		return genre;
	}

	public Book getAuthors() {
		return author;
	}
}
