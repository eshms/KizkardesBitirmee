package com.example.MicroFinance.Model.Request;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class MonthlyReportDTO {

    @NotNull(message = "Total income cannot be null")
    private BigDecimal totalIncome;

    @NotNull(message = "Total expense cannot be null")
    private BigDecimal totalExpense;

    @NotNull(message = "Savings cannot be null")
    private BigDecimal savings;

    @NotNull(message = "Top spending category cannot be null")
    private String topCategory;
}
