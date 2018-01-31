package com.hsbc.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerOrderDTO {

    private Long id;
    private Date createdDate;
    private Date updatedDate;
    @JsonIgnore
    private CustomerDTO customer;
    private List<OrderItemDTO> orderItems;
}
