package com.example.MicroFinance.Service;

import com.example.MicroFinance.Model.Category;
import com.example.MicroFinance.Model.MonthlyReport;
import com.example.MicroFinance.Model.Request.MonthlyReportDTO;
import com.example.MicroFinance.Model.Transaction;
import com.example.MicroFinance.Repository.ReportRepository;
import com.example.MicroFinance.Repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

@Service
public class ReportService {

    private final TransactionRepository transactionRepository;
    private final ReportRepository reportRepository;

    public ReportService(TransactionRepository transactionRepository, ReportRepository reportRepository) {
        this.transactionRepository = transactionRepository;
        this.reportRepository = reportRepository;
    }

    public MonthlyReport generateMonthlyReport(Long userId, int month, int year) {
        List<Transaction> userTransactions = transactionRepository.findByUserId(userId).stream()
                .filter(t -> t.getDate().getMonthValue() == month && t.getDate().getYear() == year)
                .toList();

        double totalIncome = userTransactions.stream()
                .filter(t -> t.getAmount() > 0)
                .mapToDouble(Transaction::getAmount)
                .sum();

        double totalExpense = userTransactions.stream()
                .filter(t -> t.getAmount() < 0)
                .mapToDouble(t -> Math.abs(t.getAmount()))
                .sum();

        double savings = totalIncome - totalExpense;

        // En çok harcama yapılan kategori
        Map<Category, Double> expenseByCategory = new HashMap<>();
        for (Transaction t : userTransactions) {
            if (t.getAmount() < 0) {
                String category = t.getCategory();
                double amount = Math.abs(t.getAmount());

                Category category1= new Category();
                category1.setName(category);
                expenseByCategory.put(category1, expenseByCategory.getOrDefault(category, 0.0) + amount);
            }
        }

        Category mostSpentCategory = null;
        double maxSpent = 0;
        for (Map.Entry<Category, Double> entry : expenseByCategory.entrySet()) {
            if (entry.getValue() > maxSpent) {
                maxSpent = entry.getValue();
                mostSpentCategory = entry.getKey();
            }
        }

        MonthlyReport report = new MonthlyReport();
        report.setUserId(userId);
        report.setMonth(Month.of(month));
        report.setYear(year);
        report.setTotalIncome(totalIncome);
        report.setTotalExpense(totalExpense);
        report.setSavings(savings);
        report.setMostSpentCategory(mostSpentCategory != null ? mostSpentCategory.getName() : "Belirlenemedi");
        report.setGeneratedDate(LocalDate.now());

        return reportRepository.save(report);
    }

    public List<MonthlyReport> getReportsByUserId(Long userId) {
        return reportRepository.findByUserId(userId);
    }

    public <Report> Optional<Report> getMonthlyReportByUserId(Long userId, int month, int year) {
        return reportRepository.findByUserIdAndMonthAndYear(userId, month, year);
    }

    public String exportMonthlyReportToFile(int userId, int month, int year) {
        try {
            // 1. Raporu oluştur
            MonthlyReport report = generateMonthlyReport(Long.valueOf(userId), month, year);

            // 2. JSON'a çevir
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(report);

            // 3. Dosya adı ve yolu
            String fileName = "report_user" + userId + "_" + month + "_" + year + ".json";
            String filePath = "reports/" + fileName;

            // 4. Dosyaya yaz
            File directory = new File("reports");
            if (!directory.exists()) {
                directory.mkdirs(); // klasörü oluştur
            }

            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(json);
                return filePath; // başarılıysa dosya yolu dön
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not write report to file: " + e.getMessage());
        }
    }

}

