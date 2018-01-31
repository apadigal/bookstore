package com.hsbc.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Identifiable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by swashtechltd on 31/01/2018.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Identifiable<Long> {

    @Id
    @GeneratedValue
    private Long Id;

    @Column private String firstName;

    @Column private String lastName;
    @Column private String addressLine1;
    @Column private String addressLine2;
    @Column private String city;
    @Column private String country;
    @Column private String postalCode;
    @Column private String phone;




}
