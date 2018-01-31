package com.hsbc.bookstore.dto;

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
 * Created Date: 31/01/2018 08:34
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenreDTO {

    private Long id;
    private String description;
//    @JsonIgnore
//    private List<BookDTO> books;

}
