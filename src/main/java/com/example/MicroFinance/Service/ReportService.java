package com.example.MicroFinance.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.Month;
import java.util.stream.Collectors;
import com.example.MicroFinance.Model.Report;
import com.example.MicroFinance.Repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.YearMonth;
import java.util.*;
    @Service
    public class ReportService {

        private final ReportRepository reportRepository;

        @Autowired
        public ReportService(ReportRepository reportRepository) {

            this.reportRepository = reportRepository;
        }

        public Map<String, Object> generateMonthlyReport(int year, int month) {
            YearMonth yearMonth = YearMonth.of(year, month);
            Date startDate = java.sql.Date.valueOf(yearMonth.atDay(1));
            Date endDate = java.sql.Date.valueOf(yearMonth.atEndOfMonth());

            List<Report> records = reportRepository.findByDateBetween(startDate, endDate);


            double totalIncome = records.stream()
                    .filter(r -> r.getType().equalsIgnoreCase("income"))
                    .mapToDouble(Report::getAmount)
                    .sum();

            double totalExpense = records.stream()
                    .filter(r -> r.getType().equalsIgnoreCase("expense"))
                    .mapToDouble(Report::getAmount)
                    .sum();

            double savings = totalIncome - totalExpense;

            String topCategory = (String) records.stream()
                    .filter(r -> r.getType().equalsIgnoreCase("expense"))
                    .collect(Collectors.groupingBy(Report::getCategory, Collectors.summingDouble(Report::getAmount)))
                    .entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse("Yok");

            Map<String, Object> jsonReport = new LinkedHashMap<>();
            jsonReport.put("year", year);
            jsonReport.put("month", month);
            jsonReport.put("totalIncome", totalIncome);
            jsonReport.put("totalExpense", totalExpense);
            jsonReport.put("savings", savings);
            jsonReport.put("topSpendingCategory", topCategory);

            return jsonReport;
        }

        public String getMonthlyReportAsJson(int year, int month) {
            Map<String, Object> report = generateMonthlyReport(year, month);

            StringBuilder jsonBuilder = new StringBuilder();
            jsonBuilder.append("{\n");
            int i = 0;
            int size = report.size();

            for (Map.Entry<String, Object> entry : report.entrySet()) {
                jsonBuilder.append("  \"")
                        .append(entry.getKey())
                        .append("\": ");

                if (entry.getValue() instanceof String) {
                    jsonBuilder.append("\"").append(entry.getValue()).append("\"");
                } else {
                    jsonBuilder.append(entry.getValue());
                }

                if (++i < size) {
                    jsonBuilder.append(",");
                }

                jsonBuilder.append("\n");
            }

            jsonBuilder.append("}");
            return jsonBuilder.toString();
        }
        public void backupReportsToTxt(String filePath) {
            List<Report> reports = reportRepository.findAll(); // tüm harcamaları getir

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (Report report : reports) {
                    writer.write("Tarih: " + report.getDate() +
                            ", Tutar: " + report.getAmount() +
                            ", Tür: " + report.getType() +
                            ", Kategori: " + report.getCategory());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public Object getReportsByUserId(long userId) {
            return reportRepository.findByUserId(userId);
        }

        public Optional getMonthlyReportByUserId(long userId, int month, int year) {
                  return reportRepository.findByUserIdAndMonthAndYear(userId, month, year);
        }


        public String exportMonthlyReportToFile(int userId, int month, int year) {
            return year + "-" + month + "-" + userId;
        }
    }


