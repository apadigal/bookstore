package com.hsbc.bookstore.repository;

import com.hsbc.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by swashtechltd on 30/01/2018.
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    Book findBookByTitleIgnoreCase(String title);
    Book findBookByIsbnIgnoreCase(String isbn);
}
