package com.example.MicroFinance.Model;

import lombok.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonthlyReport {

    private Long userId;
    private int year;
    private Month month;
    private double totalIncome;
    private double totalExpense;
    private double savings;
    private String topCategory;

    private Map<String, Double> categoryExpenseMap; // Kategori bazlı harcama

    public void setMostSpentCategory(String s) {
        this.topCategory = s;
    }

    public void setGeneratedDate(LocalDate now) {
        this.year = now.getYear();
        this.month = now.getMonth();
    }
}
