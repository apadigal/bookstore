package com.hsbc.bookstore.controller;

import com.hsbc.bookstore.dto.BookDTO;
import com.hsbc.bookstore.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by swashtechltd on 30/01/2018.
 */
@RestController
@RequestMapping(path = "/api/v1/store/books")
public class BookController extends AbstractController{

    @Autowired
    BookStoreService bookStoreService;

    /**
     *
     * @return
     */
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public List<BookDTO> getBooks() {
        return bookStoreService.getBooks();

    }

    /**
     *
     * @param bookDTO
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO createBook(@Validated @RequestBody BookDTO bookDTO) {
        return bookStoreService.createBook(bookDTO);
    }

    /**
     * 
     * @param id
     * @return
     */
    @GetMapping(path = "/{id}", produces = MediaTypes.HAL_JSON_VALUE )
    public BookDTO getBook(@PathVariable Long id)
    {
        return  bookStoreService.getBook(id);
    }

    
}
