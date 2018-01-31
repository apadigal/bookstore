package com.hsbc.bookstore.service;

import com.hsbc.bookstore.dto.AuthorDTO;
import com.hsbc.bookstore.dto.BookDTO;
import com.hsbc.bookstore.dto.GenreDTO;
import com.hsbc.bookstore.exception.CustomException;
import com.hsbc.bookstore.model.Author;
import com.hsbc.bookstore.model.Book;
import com.hsbc.bookstore.model.Genre;
import com.hsbc.bookstore.repository.AuthorRepository;
import com.hsbc.bookstore.repository.BookRepository;
import com.hsbc.bookstore.repository.GenreRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * $Revision: #1 $
 *
 * @Author: apadigal $
 * $Date: 31/01/2018 $
 * Created Date: 31/01/2018 13:05
 */
@RunWith(MockitoJUnitRunner.class)
public class BookStoreServiceTest {

    @Mock
    Genre genre;
    @Mock
    Author author;
    @Mock
    Book book;
    @InjectMocks
    @Spy
    BookStoreService bookStoreService;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private GenreRepository genreRepository;

    @Test
    public void getBooks() throws Exception {
        // When
        bookStoreService.getBooks();

        // Then
        Mockito.verify(bookRepository).findAll();
    }

    @Test
    public void createBook() throws Exception {
        // Given
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(1l);
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(1l);
        BookDTO bookDTO = new BookDTO();
        bookDTO.setGenre(genreDTO);
        bookDTO.setAuthor(authorDTO);
        when(genreRepository.findOne(anyLong())).thenReturn(genre);
        when(authorRepository.findOne(anyLong())).thenReturn(author);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        // When
        bookStoreService.createBook(bookDTO);

        // Then
        Mockito.verify(bookRepository).save(any(Book.class));
    }

    @Test
    public void updateBook() throws Exception {
        // Given
        when(bookRepository.findOne(anyLong())).thenReturn(book);
        when(book.getVersion()).thenReturn(1);

        BookDTO bookDTO = new BookDTO();
        bookDTO.setVersion(1);
        bookDTO.setTitle("new title");

        // When
        bookStoreService.updateBook(1l, bookDTO);

        // Then
        Mockito.verify(bookRepository).save(any(Book.class));
    }

    @Test(expected = CustomException.class)
    public void updateBook_oldVersion() {
        // Given
        when(bookRepository.findOne(anyLong())).thenReturn(book);
        when(book.getVersion()).thenReturn(1);

        BookDTO bookDTO = new BookDTO();
        bookDTO.setVersion(2);
        bookDTO.setTitle("new title");

        // When
        bookStoreService.updateBook(1l, bookDTO);
    }


}