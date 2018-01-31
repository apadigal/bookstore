package com.hsbc.bookstore.service;

import com.hsbc.bookstore.dto.BookDTO;
import com.hsbc.bookstore.dto.CustomerOrderDTO;
import com.hsbc.bookstore.dto.OrderItemDTO;
import com.hsbc.bookstore.model.Book;
import com.hsbc.bookstore.model.Customer;
import com.hsbc.bookstore.model.CustomerOrder;
import com.hsbc.bookstore.repository.BookRepository;
import com.hsbc.bookstore.repository.CustomerOrderRepository;
import com.hsbc.bookstore.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
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
public class OrderServiceTest {

    @Mock
    CustomerOrderRepository customerOrderRepository;
    @Mock
    BookRepository bookRepository;
    @Mock
    CustomerOrder customerOrder;
    @Mock
    Customer customer;
    @Mock
    Book book;
    @InjectMocks
    @Spy
    OrderService orderService;
    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void createCustomerOrder() throws Exception {

        CustomerOrderDTO customerOrderDTO =  new CustomerOrderDTO();
       
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(1l);
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setBook(bookDTO);

        customerOrderDTO.setOrderItems(Arrays.asList(orderItemDTO));

        when(customerRepository.findOne(anyLong())).thenReturn(customer);
        when(bookRepository.findOne(anyLong())).thenReturn(book);

        when(customerOrderRepository.save(any(CustomerOrder.class))).thenReturn(customerOrder);

        orderService.createCustomerOrder(1l, customerOrderDTO);
        Mockito.verify(customerOrderRepository).save(any(CustomerOrder.class));
    }

}