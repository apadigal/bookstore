package com.hsbc.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * $Revision: #1 $
 *
 * @Author: apadigal $
 * $Date: 31/01/2018 $
 * Created Date: 31/01/2018 08:34
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Genre{
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "genre")
    private List<Book> books;
}
