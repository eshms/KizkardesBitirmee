package com.example.MicroFinance.Repository;

import com.example.MicroFinance.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByDateBetween(LocalDate start, LocalDate end);
    List<Transaction> findByUserId(Long userId);

}
