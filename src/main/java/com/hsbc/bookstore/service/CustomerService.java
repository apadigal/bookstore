package com.hsbc.bookstore.service;

import com.hsbc.bookstore.dto.CustomerDTO;
import com.hsbc.bookstore.model.Customer;
import com.hsbc.bookstore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by swashtechltd on 30/01/2018.
 */
@Service
public class CustomerService extends AbstractService {
    @Autowired
    private CustomerRepository customerRepository;


    /**
     *
     * @return
     */
    public List<CustomerDTO> getCustomers()
    {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(this::convertToCustomerDTO)
        .collect(Collectors.toList());
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO)
    {
        Customer customer = convertToObject(customerDTO, Customer.class);
        customer = customerRepository.save(customer);
        customerDTO = convertToObject(customer, CustomerDTO.class);
        return customerDTO;
    }

    private CustomerDTO convertToCustomerDTO(Customer customer)
    {
        return convertToObject(customer, CustomerDTO.class);
    }


}
