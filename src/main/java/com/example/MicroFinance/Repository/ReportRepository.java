package com.example.MicroFinance.Repository;

import com.example.MicroFinance.Model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Month;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByUserId(Long userId);
    Report findById(long id);
    List<Report> findByYear(int year);
    List<Report> findByMonth(int month);

    List<Report> findByDateBetween(Date startDate, Date endDate);

    Optional<Report> findByUserIdAndMonthAndYear(Long userId, Integer month, Integer year);

}
