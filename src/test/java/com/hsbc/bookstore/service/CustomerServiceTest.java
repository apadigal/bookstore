package com.hsbc.bookstore.service;

import com.hsbc.bookstore.dto.CustomerDTO;
import com.hsbc.bookstore.model.Customer;
import com.hsbc.bookstore.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * $Revision: #1 $
 *
 * @Author: apadigal $
 * $Date: 31/01/2018 $
 * Created Date: 31/01/2018 13:06
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    Customer customer;
    @InjectMocks
    @Spy
    CustomerService customerService;
    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void getCustomers() throws Exception {
        // When
        customerService.getCustomers();

        // Then
        Mockito.verify(customerRepository).findAll();
    }

    @Test
    public void createCustomer() throws Exception {
        // Given
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        // When
        customerService.createCustomer(new CustomerDTO());

        // Then
        Mockito.verify(customerRepository).save(any(Customer.class));
    }

}