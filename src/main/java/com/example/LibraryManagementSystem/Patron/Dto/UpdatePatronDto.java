package com.example.LibraryManagementSystem.Patron.Dto;

import com.example.LibraryManagementSystem.Patron.ContactInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdatePatronDto  {
    @Size(max = 50, message = "Name should not exceed 50 characters")
    private String name;

    @Valid
    private ContactInfo contactInfo;
}
