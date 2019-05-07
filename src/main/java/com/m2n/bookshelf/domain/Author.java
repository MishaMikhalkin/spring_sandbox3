package com.m2n.bookshelf.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "AUTHORS")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id", "books"})
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ToString.Include
	private String name;

	public Author(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "id")
	@ToString.Exclude
	private Set<Book> books;
}

