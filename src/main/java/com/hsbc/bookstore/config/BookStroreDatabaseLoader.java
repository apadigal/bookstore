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

import com.hsbc.bookstore.model.*;
import com.hsbc.bookstore.repository.*;
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
    CommandLineRunner initDatabase(BookRepository bookRepository,
								   AuthorRepository authorRepository,
								   GenreRepository genreRepository,
								   CustomerRepository customerRepository,
								   CustomerOrderRepository customerOrderRepository) {
		return args -> {
			Genre genre = genreRepository.save( Genre.builder().description("action").build());
			Author author = authorRepository.save(Author.builder().firstName("Anand").lastName("Padigala").build());

			genreRepository.save(genre);
			authorRepository.save(author);
			Book book1 = bookRepository.save(Book.builder().author(author).genre(genre).title("Book1:book1").isbn("100001").price(10.5).build());
			Book book2 = bookRepository.save(Book.builder().author(author).genre(genre).title("Book1:book2").isbn("200001").price(12.5).build());

			genre.setBooks(Arrays.asList(book1, book2));
			genreRepository.save(genre);

			Customer customer = customerRepository.save(Customer.builder().firstName("anand")
					.lastName("padigala")
					.addressLine1("add")
					.addressLine2("line")
					.city("london")
					.country("uk")
					.postalCode("W1F999")
					.phone("888888").build());

			customerRepository.save(customer);


			OrderItem orderItem1 = OrderItem.builder().book(book1).quantity(10).build();
			OrderItem orderItem2 = OrderItem.builder().book(book2).quantity(2).build();

			CustomerOrder customerOrder = CustomerOrder.builder().customer(customer).orderItems(Arrays.asList(orderItem1, orderItem2)).build();
			orderItem1.setCustomerOrder(customerOrder);
			orderItem2.setCustomerOrder(customerOrder);
			customerOrderRepository.save(customerOrder);


		};
	}
}
