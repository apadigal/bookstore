package com.hsbc.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by swashtechltd on 30/01/2018.
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Author implements Identifiable<Long> {

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
