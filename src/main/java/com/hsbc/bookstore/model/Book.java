package com.hsbc.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by swashtechltd on 30/01/2018.
 */

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Book implements Identifiable<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true, length = 128)
    private String title;

    @Column(nullable = false, unique = true, length = 128)
    private String isbn;

    @Column(nullable = false)
    private Double price;


    @Column
    @CreatedDate
    private Date createdDate;

    @Column
    @UpdateTimestamp
    private Date updatedDate;

    @Version
    @Column
    private Integer version;


    @ManyToOne
    @JoinColumn(name="author_id", nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name="genre_id", nullable = false)
    private Genre genre;


}
