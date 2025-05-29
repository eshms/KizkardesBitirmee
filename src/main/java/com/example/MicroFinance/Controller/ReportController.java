package com.example.MicroFinance.Controller;

import com.example.MicroFinance.Model.Request.MonthlyReportDTO;
import com.example.MicroFinance.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // ✔ Aylık özet rapor: gelir, gider, tasarruf
    @GetMapping("/monthly-summary")
    public MonthlyReportDTO getMonthlySummary(
            @RequestParam int userId,
            @RequestParam int month,
            @RequestParam int year) {
        return (MonthlyReportDTO) reportService.getReportsByUserId((long) userId);
    }

    // ✔ En çok harcama yapılan kategori
    @GetMapping("/top-category")
    public Optional getTopSpendingCategory(
            @RequestParam int userId,
            @RequestParam int month,
            @RequestParam int year) {
        return reportService.getMonthlyReportByUserId((long) userId, month, year);
    }

    // ✔ Aylık özet JSON dosyası olarak dışa aktar (rapor kaydı)
    @GetMapping("/export")
    public String exportMonthlyReport(
            @RequestParam int userId,
            @RequestParam int month,
            @RequestParam int year) {
        return reportService.exportMonthlyReportToFile(userId, month, year);
    }
}
