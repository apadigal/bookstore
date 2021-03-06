/*
 * SIEE - PMDB Management
 *
 * Author.java
 *
 * 2017 SIEE. All Rights Reserved
 */
// ---- Package ---------------------------------------------------------------
package com.hsbc.bookstore.model;
// ---- Import Statements -----------------------------------------------------

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * $Revision: #1 $
 *
 * @Author: apadigal $
 * $Date: 31/01/2018 $
 * Created Date: 31/01/2018 08:32
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Author{

    @Id
    @GeneratedValue
    private  Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private Set<Book> books;

}