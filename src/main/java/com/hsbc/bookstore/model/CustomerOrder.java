package com.hsbc.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
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
public class CustomerOrder {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @CreatedDate
    private Date createdDate;

    @Column
    @UpdateTimestamp
    private Date updatedDate;


    @ManyToOne(optional = false)
    @JoinColumn(nullable = false, name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

}
