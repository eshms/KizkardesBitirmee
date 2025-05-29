package com.example.MicroFinance.Controller;

import com.example.MicroFinance.Model.Transaction;
import com.example.MicroFinance.Service.TransactionService;
import jakarta.transaction.InvalidTransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // ✔ Yeni gelir/gider ekleme
    @PostMapping("/add")
    public Transaction addTransaction(@RequestBody Transaction transaction) throws InvalidTransactionException {
        return transactionService.addTransaction(transaction);
    }

    // ✔ Belirli bir kullanıcıya ait tüm işlemleri getir
    @GetMapping("/user/{userId}")
    public List<Transaction> getTransactionsByUser(@PathVariable int userId) {
        return transactionService.getTransactionsByUserId((long) userId);
    }

    // ✔ Belirli bir işlemi ID ile getir
    @GetMapping("/{id}")
    public Optional<Transaction> getTransactionById(@PathVariable int id) {
        return transactionService.getTransactionById(Long.valueOf(id));
    }

    // ✔ Belirli bir işlemi güncelle
    @PutMapping("/update/{id}")
    public Transaction updateTransaction(@PathVariable int id, @RequestBody Transaction transaction) throws InvalidTransactionException {
        return transactionService.updateTransaction((long) id, transaction);
    }

    // ✔ Belirli bir işlemi sil
    @DeleteMapping("/delete/{id}")
    public Long deleteTransaction(@PathVariable int id) throws InvalidTransactionException {
        return transactionService.deleteTransaction(Long.valueOf(id));
    }

}
