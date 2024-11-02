package com.example.LibraryManagementSystem.Transaction;

import com.example.LibraryManagementSystem.Book.Book;
import com.example.LibraryManagementSystem.Book.BookRepository;
import com.example.LibraryManagementSystem.Common.Exceptions.EntityNotFoundException;
import com.example.LibraryManagementSystem.Transaction.Dto.TransactionQueryFilterDto;
import com.example.LibraryManagementSystem.Patron.Patron;
import com.example.LibraryManagementSystem.Patron.PatronRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class TransactionService {
    final
    TransactionRepository transactionRepository;

    final
    BookRepository bookRepository;

    final
    PatronRepository patronRepository;

    public TransactionService(TransactionRepository transactionRepository, BookRepository bookRepository, PatronRepository patronRepository) {
        this.transactionRepository = transactionRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }


    public Page<Transaction> getAllBorrowings(@Valid TransactionQueryFilterDto query) {

        Sort sort = query.getSortOrder().equalsIgnoreCase("asc") ?
                Sort.by(query.getSortField()).ascending() : Sort.by(query.getSortField()).descending();

        Pageable pageable = PageRequest.of(query.getPage(), query.getPageSize(), sort);

        return this.transactionRepository.findAll(pageable);
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transaction", id));
    }


    public Transaction borrowBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book",bookId));

        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new EntityNotFoundException("Patron",patronId));
        Optional<Transaction> transaction = transactionRepository.findByBookIdAndPatronIdAndReturnDateIsNull(bookId, patronId);

        if (transaction.isPresent()&& transaction.get().getReturnDate()==null) {
            return transaction.orElse(null);
        }
        Transaction record = new Transaction();
        record.setBook(book);
        record.setPatron(patron);
        record.setBorrowingDate(LocalDate.now());

        return  transactionRepository.save(record);
    }

    public Transaction returnBook(Long bookId, Long patronId) {
        Transaction transaction = transactionRepository.findByBookIdAndPatronIdAndReturnDateIsNull(bookId, patronId)
                .orElseThrow(() -> new RuntimeException("Transaction record not found"));
        transaction.setReturnDate(LocalDate.now());
        return  transactionRepository.save(transaction);
    }


}
