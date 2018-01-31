/*
 * SIEE - PMDB Management
 *
 * OrderItem.java
 *
 * 2017 SIEE. All Rights Reserved
 */
// ---- Package ---------------------------------------------------------------
package com.hsbc.bookstore.model;
// ---- Import Statements -----------------------------------------------------

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * $Revision: #1 $
 *
 * @Author: apadigal $
 * $Date: 31/01/2018 $
 * Created Date: 31/01/2018 08:32
 */

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne(optional =  false)
    @JoinColumn(nullable = false, name = "book_id", updatable = false)
    private Book book;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, name = "order_id", updatable = false)
    private CustomerOrder customerOrder;
}