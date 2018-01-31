/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hsbc.bookstore.config;

import com.hsbc.bookstore.model.Author;
import com.hsbc.bookstore.model.Book;
import com.hsbc.bookstore.model.Genre;
import com.hsbc.bookstore.repository.AuthorRepository;
import com.hsbc.bookstore.repository.BookRepository;
import com.hsbc.bookstore.repository.GenreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Greg Turnquist
 */
@Component
class BookStroreDatabaseLoader {

	@Bean
    CommandLineRunner initDatabase(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
		return args -> {
			Genre genre = genreRepository.save( Genre.builder().description("action").build());
			Author author = authorRepository.save(Author.builder().firstName("Anand").lastName("Padigala").build());

			Book book1 = bookRepository.save(Book.builder().author(author).genre(genre).title("Book:book1").isbn("100001").price(10.5).build());
			Book book2 = bookRepository.save(Book.builder().author(author).genre(genre).title("Book:book2").isbn("200001").price(12.5).build());

			genre.setBooks(Arrays.asList(book1, book2));
			genreRepository.save(genre);

		};
	}
}
