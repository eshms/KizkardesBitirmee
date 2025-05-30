package com.example.MicroFinance.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "transaction")
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotBlank(message = "Description cannot be null")
    @Size(min = 2, max = 100, message = "Description must be between 2 and 100 characters")
    private String description; // Gelir veya gider açıklaması

    @NotBlank(message = "Amount cannot be null")
    private double amount; // Tutar

    @NotBlank(message = "Transaction type cannot be null")
    @Size(min = 3, max = 10, message = "Type must be 'income' or 'expense'")
    private String type; // 'income' veya 'expense'

    @NotBlank(message = "Date cannot be null")
    private LocalDate date; // İşlem tarihi

    // Getter ve Setter metodları

    public @NotBlank(message = "Description cannot be null") @Size(min = 2, max = 100, message = "Description must be between 2 and 100 characters") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "Description cannot be null") @Size(min = 2, max = 100, message = "Description must be between 2 and 100 characters") String description) {
        this.description = description;
    }

    public @NotBlank(message = "Transaction type cannot be null") @Size(min = 3, max = 10, message = "Type must be 'income' or 'expense'") String getType() {
        return type;
    }

    public void setType(@NotBlank(message = "Transaction type cannot be null") @Size(min = 3, max = 10, message = "Type must be 'income' or 'expense'") String type) {
        this.type = type;
    }

    @NotBlank(message = "Amount cannot be null")
    public double getAmount() {
        return amount;
    }

    public void setAmount(@NotBlank(message = "Amount cannot be null") double amount) {
        this.amount = amount;
    }

    @NotBlank(message = "Date cannot be null")
    public LocalDate getDate() {
        return date;
    }

    public void setDate(@NotBlank(message = "Date cannot be null") LocalDate date) {
        this.date = date;
    }

    private String category;
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category= category;
    }
}
