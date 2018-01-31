/*
 * SIEE - PMDB Management
 *
 * Book.java
 *
 * 2017 SIEE. All Rights Reserved
 */
// ---- Package ---------------------------------------------------------------
package com.hsbc.bookstore.model;
// ---- Import Statements -----------------------------------------------------

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * $Revision: #1 $
 *
 * @Author: apadigal $
 * $Date: 31/01/2018 $
 * Created Date: 31/01/2018 08:34
 */
@Entity
@Getter
@Setter
@ToString(exclude = {"author","genre"})
@EqualsAndHashCode(exclude = {"author","genre"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book{

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
