package com.hsbc.bookstore.controller;

import com.hsbc.bookstore.model.Book;
import com.hsbc.bookstore.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by swashtechltd on 30/01/2018.
 */
@RestController
@RequestMapping(path = "/api/v1/store")
public class BookStoreController {

    @Autowired
    BookStoreService bookStoreService;

    @GetMapping(path = "/book", produces = { "application/json", "application/xml" })
    public List<Book> getBooks()
    {
        return bookStoreService.getBooks();

    }

    @PutMapping(path = "/book/{id}", produces = { "application/json", "application/xml" })
    public List<Book> updateBook ( @PathVariable Integer id, @RequestBody Book book)
    {
        return bookStoreService.getBooks();

    }
}
