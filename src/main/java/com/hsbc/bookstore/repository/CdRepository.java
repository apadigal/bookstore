package com.hsbc.bookstore.repository;

import com.hsbc.bookstore.model.Cd;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by swashtechltd on 30/01/2018.
 */
public interface CdRepository extends JpaRepository<Cd, Integer> {
}
