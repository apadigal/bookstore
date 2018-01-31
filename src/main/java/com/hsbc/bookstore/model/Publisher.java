package com.hsbc.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.util.List;

/**
 * Created by swashtechltd on 31/01/2018.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Publisher implements Identifiable<Long> {

    @Id
    @GeneratedValue
    private Long Id;

    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String city;
    @Column
    private String country;
    @Column
    private String phone;

    @JsonIgnore
    @OneToMany(mappedBy = "publisher")
    private List<Book> books;

}
