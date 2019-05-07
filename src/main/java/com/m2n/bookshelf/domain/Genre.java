package com.m2n.bookshelf.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "GENRES")
@NoArgsConstructor
@Data
@EqualsAndHashCode(exclude = {"id", "books"})
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ToString.Exclude
	private int id;

	@ToString.Include
	private String name;

	public Genre(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "id")
	@ToString.Exclude
	private Set<Book> books;
}
