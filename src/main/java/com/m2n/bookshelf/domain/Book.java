package com.m2n.bookshelf.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
@NoArgsConstructor
public class Book {
	@Id
	private String id;
	private String name;
	private int yearOfCreated;

	private String genre;

	@DBRef
	private Author authorName;

    public Book(String uuid, String name, int year, String genre, Author author) {
    	this.name = name;
    	this.yearOfCreated = year;
    	this.genre = genre;
    	this.authorName = author;
	}
}
