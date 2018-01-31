package com.hsbc.bookstore.service;

import com.hsbc.bookstore.dto.BookDTO;
import com.hsbc.bookstore.exception.CustomException;
import com.hsbc.bookstore.model.Book;
import com.hsbc.bookstore.repository.AuthorRepository;
import com.hsbc.bookstore.repository.BookRepository;
import com.hsbc.bookstore.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by swashtechltd on 30/01/2018.
 */
@Service
public class BookStoreService extends AbstractService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    /**
     *
     * @return
     */
    public List<BookDTO> getBooks()
    {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(this::convertToBookDTO)
        .collect(Collectors.toList());
    }

    public BookDTO getBook(Long id)
    {
        return convertToObject(bookRepository.findOne(id), BookDTO.class);
    }

    public BookDTO createBook(BookDTO bookDTO)
    {
        Book book = validateAndGetBook(bookDTO);

        if(book.getGenre().getId() != null) {
            book.setGenre(genreRepository.findOne(book.getGenre().getId()));
        }
        if(book.getAuthor().getId() != null) {
            book.setAuthor(authorRepository.findOne(book.getAuthor().getId()));
        }
        book = bookRepository.save(book);
        return convertToObject(book, BookDTO.class);
    }

    private Book validateAndGetBook(BookDTO bookDTO)
    {
        if(bookRepository.findBookByTitleIgnoreCase(bookDTO.getTitle()) != null)
            throw new CustomException("Book with title :"+ bookDTO.getTitle() +" already exists.");

        if(bookRepository.findBookByIsbnIgnoreCase(bookDTO.getIsbn()) != null)
            throw new CustomException("Book with ISBN :"+ bookDTO.getIsbn() +" already exists.");

        return convertToObject(bookDTO, Book.class);
    }

    private BookDTO convertToBookDTO(Book book)
    {
        return convertToObject(book, BookDTO.class);
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
