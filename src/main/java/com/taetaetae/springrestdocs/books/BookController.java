package com.taetaetae.springrestdocs.books;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

	@GetMapping("/book/{id}")
	public Book getABook(@PathVariable Integer id) {
		Book book = new Book();
		book.setId(id);
		book.setTitle("spring rest docs in spring boot");
		book.setAuthor("taetaetae");
		return book;
	}
}
