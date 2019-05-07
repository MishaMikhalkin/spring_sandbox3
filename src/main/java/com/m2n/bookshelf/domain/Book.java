package com.m2n.bookshelf.domain;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Table(name = "BOOKS")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
@ToString(exclude = "id")
public class Book {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(name = "created")
	private int yearOfCreated;

    @ManyToOne(targetEntity = Genre.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "genre_id", referencedColumnName = "id")
	private Genre genre;

	@ManyToOne(targetEntity = Author.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "author_id", referencedColumnName = "id")
	private Author authorName;

    public Book(String name, int year, Genre genre, Author author) {
    	this.name = name;
    	this.yearOfCreated = year;
    	this.genre = genre;
    	this.authorName = author;
	}
}
