package com.example.LibraryManagementSystem.Book.Dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class CreateBookDto implements Serializable {
    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title should not exceed 100 characters")
    protected String title;

    @NotBlank(message = "Author is required")
    @Size(max = 50, message = "Author should not exceed 50 characters")
    protected String author;

    @NotNull(message = "Publication year is required")
    @Positive(message = "Publication year must be positive")
    protected Integer publicationYear;

    @NotBlank(message = "ISBN is required")
    @Pattern(regexp = "\\d{3}-\\d{10}", message = "ISBN format should be '###-##########'")
    protected String isbn;

    public @NotBlank(message = "Title is required") @Size(max = 100, message = "Title should not exceed 100 characters") String getTitle() {
        return title;
    }

}
