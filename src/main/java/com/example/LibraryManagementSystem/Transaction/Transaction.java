package com.example.LibraryManagementSystem.Transaction;


import com.example.LibraryManagementSystem.Book.Book;
import com.example.LibraryManagementSystem.Patron.Patron;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    @JsonIgnoreProperties("transactions")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "patron_id", nullable = false)
    @JsonIgnoreProperties("transactions")
    private Patron patron;

    @Column(nullable = false)
    private LocalDate borrowingDate;

    @Column
    private LocalDate returnDate;


}
