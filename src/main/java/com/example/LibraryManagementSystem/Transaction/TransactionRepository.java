package com.example.LibraryManagementSystem.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findByBookIdAndPatronIdAndReturnDateIsNull(Long bookId, Long patronId);

}
