/*
 * SIEE - PMDB Management
 *
 * OrderService.java
 *
 * 2017 SIEE. All Rights Reserved
 */
// ---- Package ---------------------------------------------------------------
package com.hsbc.bookstore.service;
// ---- Import Statements -----------------------------------------------------

import com.hsbc.bookstore.dto.CustomerOrderDTO;
import com.hsbc.bookstore.model.Customer;
import com.hsbc.bookstore.model.CustomerOrder;
import com.hsbc.bookstore.repository.BookRepository;
import com.hsbc.bookstore.repository.CustomerOrderRepository;
import com.hsbc.bookstore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * $Revision: #1 $
 *
 * @Author: apadigal $
 * $Date: 31/01/2018 $
 * Created Date: 31/01/2018 12:27
 */
@Service
public class OrderService extends AbstractService
{
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerOrderRepository customerOrderRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    BookRepository bookRepository;
        
    public CustomerOrderDTO createCustomerOrder(Long customerId, CustomerOrderDTO customerOrderDTO)
    {
        Customer customer = customerRepository.findOne(customerId);
        final CustomerOrder customerOrder = convertToObject(customerOrderDTO, CustomerOrder.class);
        customerOrder.getOrderItems().stream().forEach(e ->
        {
            e.setCustomerOrder(customerOrder);
            e.setBook(bookRepository.findOne(e.getBook().getId()));
        });

        customerOrder.setCustomer(customer);
        CustomerOrder customerOrderSaved = customerOrderRepository.save(customerOrder);

        return convertToObject(customerOrderSaved, CustomerOrderDTO.class);
    }

}