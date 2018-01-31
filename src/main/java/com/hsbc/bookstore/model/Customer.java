package com.hsbc.bookstore.model;

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
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer{

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

    @OneToMany(mappedBy = "customer")
    private List<CustomerOrder> customerOrders;

}
