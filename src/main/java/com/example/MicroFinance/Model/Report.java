package com.example.MicroFinance.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.Map;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

@Entity
@Table(name = "reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private int year;
    private int month;
    private double totalIncome;

    private double totalExpense;

    private double savings;

    private String topCategory;

    @Temporal(TemporalType.DATE)
    private Date date;

    @ElementCollection
    @CollectionTable(name = "report_category_expenses", joinColumns = @JoinColumn(name = "report_id"))
    @MapKeyColumn(name = "category")
    @Column(name = "expense")
    private Map<String, Double> categoryExpenseMap;

    public void setMostSpentCategory(String s) {
        this.topCategory = s;
    }

    public void setGeneratedDate(int year, int month) {
        this.year = year;
        this.month = month;
    }

    public String getType() {
        return "Monthly";
    }

    public double getAmount() {
        return totalIncome - totalExpense;
    }

    public Object getCategory() {
        return categoryExpenseMap;
    }

    public String getDate() {
        return month + " " + year; // Örn: MAY 2025
    }

}
