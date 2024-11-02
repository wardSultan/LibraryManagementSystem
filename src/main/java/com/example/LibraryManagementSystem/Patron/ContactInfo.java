package com.example.LibraryManagementSystem.Patron;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Data
@Setter
@Getter
public class ContactInfo {
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "\\d{10,15}", message = "Phone should contain only digits and be between 10 and 15 characters")
    private String phone;
}