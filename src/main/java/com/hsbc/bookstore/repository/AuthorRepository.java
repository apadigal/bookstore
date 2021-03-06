package com.hsbc.bookstore.repository;

import com.hsbc.bookstore.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by swashtechltd on 30/01/2018.
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
