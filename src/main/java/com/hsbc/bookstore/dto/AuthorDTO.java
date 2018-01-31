/*
 * SIEE - PMDB Management
 *
 * Author.java
 *
 * 2017 SIEE. All Rights Reserved
 */
// ---- Package ---------------------------------------------------------------
package com.hsbc.bookstore.dto;
// ---- Import Statements -----------------------------------------------------

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * $Revision: #1 $
 *
 * @Author: apadigal $
 * $Date: 31/01/2018 $
 * Created Date: 31/01/2018 08:32
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AuthorDTO {

    private Long id;
    private String firstName;
    private String lastName;
//    @JsonIgnore
//    private Set<BookDTO> books;

}