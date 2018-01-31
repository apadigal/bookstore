package com.hsbc.bookstore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hsbc.bookstore.BookstoreApplication;
import com.hsbc.bookstore.dto.AuthorDTO;
import com.hsbc.bookstore.dto.BookDTO;
import com.hsbc.bookstore.dto.GenreDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by IntelliJ IDEA.
 * $Revision: #1 $
 *
 * @Author: apadigal $
 * $Date: 31/01/2018 $
 * Created Date: 31/01/2018 14:50
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = BookstoreApplication.class)
@Slf4j
public class BookControllerTest {

    protected MockMvc mockMvc;
    @Autowired
    private FilterChainProxy springSecurityFilterChain;
    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
    }

    @Test
    public void getBooks() throws Exception {

        ResultActions resultActions = mockMvc.perform(get("/api/v1/store/books/")
                .with(httpBasic("user", "password")))
                .andExpect(status().isOk());
    }

    @Test
    public void createBook() throws Exception {

        BookDTO bookDTO = BookDTO.builder().title("Title:"+ UUID.randomUUID().toString())
                .price(12.4)
                .isbn(UUID.randomUUID().toString())
                .author(AuthorDTO.builder().id(1L).build())
                .genre(GenreDTO.builder().id(1L).build())
                .build();
        String payloadString = asJsonString(bookDTO);
        log.debug(payloadString);
        ResultActions resultActions = mockMvc.perform(post("/api/v1/store/books/")
        .with(httpBasic("admin","admin"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(payloadString)).andExpect(status().isCreated());



    }

    @Test
    public void getBook() throws Exception {
    }

    public String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Configuration
    public static class TestContext
    {
        @Bean
        public static PropertySourcesPlaceholderConfigurer properties(){
          return new PropertySourcesPlaceholderConfigurer();
        }
    }



}