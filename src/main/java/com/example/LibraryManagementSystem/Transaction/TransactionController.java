package com.example.LibraryManagementSystem.Transaction;


import com.example.LibraryManagementSystem.Transaction.Dto.TransactionQueryFilterDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transactions")
@Validated
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public Page<Transaction> getAllBorrowings(@Valid TransactionQueryFilterDto query) {
        return new ResponseEntity<>(this.transactionService.getAllBorrowings(query), HttpStatus.OK).getBody();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getPatronById(@PathVariable Long id) {
        Transaction transaction = transactionService.getTransactionById(id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PostMapping("borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<Transaction> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {

        return new ResponseEntity<>(transactionService.borrowBook(bookId, patronId), HttpStatus.OK);
    }

    @PutMapping("return/{bookId}/patron/{patronId}")
    public ResponseEntity<Transaction> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        return new ResponseEntity<>( transactionService.returnBook(bookId, patronId), HttpStatus.OK);
    }
}
