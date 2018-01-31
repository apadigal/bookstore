/*
 * SIEE - PMDB Management
 *
 * CustomerController.java
 *
 * 2017 SIEE. All Rights Reserved
 */
// ---- Package ---------------------------------------------------------------
package com.hsbc.bookstore.controller;
// ---- Import Statements -----------------------------------------------------

import com.hsbc.bookstore.dto.CustomerDTO;
import com.hsbc.bookstore.dto.CustomerOrderDTO;
import com.hsbc.bookstore.service.CustomerService;
import com.hsbc.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * $Revision: #1 $
 *
 * @Author: apadigal $
 * $Date: 31/01/2018 $
 * Created Date: 31/01/2018 11:38
 */
@RestController
@RequestMapping(path = "/api/v1/store/customers")
public class CustomerController extends AbstractController{

    @Autowired
    CustomerService customerService;

    @Autowired
    OrderService orderService;

    /**
     *
     * @return
     */
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public List<CustomerDTO> findAll() {
        return customerService.getCustomers();

    }

    /**
     *
     * @param customerDTO
     * @return
     */
    @PostMapping(produces = {"application/json", "application/xml"})
    public CustomerDTO createCustomer(@Validated @RequestBody CustomerDTO customerDTO) {
        return customerService.createCustomer(customerDTO);
    }

    /**
     *
     * @param id
     * @param customerOrderDTO
     * @return
     */
    @PostMapping(path = "/{id}/orders", produces = {"application/json", "application/xml"})
    public CustomerOrderDTO createCustomerOrder(@PathVariable Long id, @Validated @RequestBody CustomerOrderDTO customerOrderDTO) {
        return orderService.createCustomerOrder(id, customerOrderDTO);
    }

}