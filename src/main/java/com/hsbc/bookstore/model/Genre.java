package com.hsbc.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.util.List;

/**
 * Created by swashtechltd on 30/01/2018.
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Genre implements Identifiable<Long> {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "genre")
    private List<Book> books;
}
