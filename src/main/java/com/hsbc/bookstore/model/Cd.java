package com.hsbc.bookstore.model;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by swashtechltd on 30/01/2018.
 */

@Entity
@Data
public class Cd {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, unique = true, length = 128)
    private String name;

    @Column(nullable = false, length = 64)
    private String author;

    @Column
    @CreatedDate
    private Date createdDate;

    @Column
    @UpdateTimestamp
    private Date updatedDate;

    @Version
    @Column
    private Integer version;



}
