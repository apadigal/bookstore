package com.hsbc.bookstore.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by swashtechltd on 30/01/2018.
 */
@Getter
@Setter
@ToString(exclude = {"author","genre"})
@EqualsAndHashCode(exclude = {"author","genre"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO{

    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String isbn;
    @NotNull
    private Double price;
    private Date createdDate;
    private Date updatedDate;
    private Integer version;
    @NotNull
    private AuthorDTO author;
    @NotNull
    private GenreDTO genre;
}
