package com.example.MicroFinance.Repository;

import com.example.MicroFinance.Model.MonthlyReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<MonthlyReport, Long> {
    List<MonthlyReport> findByUserId(Long userId);
    MonthlyReport findById(long id);
    List<MonthlyReport> findByYear(int year);
    List<MonthlyReport> findByMonth(int month);

    <Report> Optional<Report> findByUserIdAndMonthAndYear(Long userId, int month, int year);
}
