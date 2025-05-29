package com.example.MicroFinance.Service;

import com.example.MicroFinance.Model.Transaction;
import com.example.MicroFinance.Repository.TransactionRepository;
import jakarta.transaction.InvalidTransactionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    //Yeni bir gelir veya gider kaydı ekler.
    public Transaction addTransaction(Transaction transaction) throws InvalidTransactionException {
        if (transaction.getAmount() == 0) {
            throw new InvalidTransactionException("Tutar 0 olamaz!");
        }
        if (transaction.getCategory() == null || transaction.getDate() == null) {
            throw new InvalidTransactionException("Kategori ve tarih boş olamaz!");
        }
        return transactionRepository.save(transaction);
    }

    //Tüm işlemleri listeler.
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    //Belirli bir kullanıcıya ait işlemleri getirir.
    public List<Transaction> getTransactionsByUserId(Long userId) {
        return transactionRepository.findByUserId(userId);
    }

    //Belirli bir aya ait işlemleri filtreler.
    public List<Transaction> getMonthlyTransactions(Long userId, int month) {
        return transactionRepository.findByUserId(userId).stream()
                .filter(t -> t.getDate().getMonthValue() == month)
                .toList();
    }

    //ID’ye göre işlem getirir.
    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    //Mevcut işlemi günceller.

    public Transaction updateTransaction(Long id, Transaction newTransaction) throws InvalidTransactionException {
        return transactionRepository.findById(id)
                .map(transaction -> {
                    transaction.setAmount(newTransaction.getAmount());
                    transaction.setDescription(newTransaction.getDescription());
                    transaction.setDate(newTransaction.getDate());
                    transaction.setCategory(newTransaction.getCategory());
                    return transactionRepository.save(transaction);
                })
                .orElseThrow(() -> new InvalidTransactionException("İşlem bulunamadı: ID = " + id));
    }

    //işlem siler.
    public Long deleteTransaction(Long id) throws InvalidTransactionException {
        if (!transactionRepository.existsById(id)) {
            throw new InvalidTransactionException("Silinecek işlem bulunamadı: ID = " + id);
        }
        transactionRepository.deleteById(id);
        return id;
    }

}
