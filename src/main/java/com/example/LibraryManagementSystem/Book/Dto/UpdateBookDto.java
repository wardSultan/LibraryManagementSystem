package com.example.LibraryManagementSystem.Book.Dto;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
public class UpdateBookDto  implements Serializable {

    @Size(max = 100, message = "Title should not exceed 100 characters")
    private String title;


    @Size(max = 50, message = "Author should not exceed 50 characters")
    private String author;


    @Positive(message = "Publication year must be positive")
    private Integer publicationYear;


    @Pattern(regexp = "\\d{3}-\\d{10}", message = "ISBN format should be '###-##########'")
    private String isbn;
}
