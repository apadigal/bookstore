package com.hsbc.bookstore.service;

import com.hsbc.bookstore.dto.BookDTO;
import com.hsbc.bookstore.exception.CustomException;
import com.hsbc.bookstore.model.Book;
import com.hsbc.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by swashtechltd on 30/01/2018.
 */
@Service
public class BookStoreService {
    @Autowired
    private BookRepository bookRepository;

    /**
     *
     * @return
     */
    public List<Book> getBooks()
    {
        return bookRepository.findAll();
    }

    /**
     *
     * @param id
     * @param bookDTO
     * @return
     */
    public Book updateBook(Long id, BookDTO bookDTO)
    {
        Book book = bookRepository.findOne(id);
        book.setTitle(bookDTO.getTitle());

        if(!book.getVersion().equals(bookDTO.getVersion()))
            throw new CustomException("Stale data error");

        return bookRepository.save(book);
    }

}
