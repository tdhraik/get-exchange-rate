package com.searchmetrics.exchange.aggregate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class HistoricalExchangeRate {

    @Id
    private String id;

    private String fromCurrency;

    private String toCurrency;

    private BigDecimal rate;

    @Indexed
    private LocalDate date;

    @Override
    public String toString() {
        return "HistoricalExchangeRate{" +
                "id='" + id + '\'' +
                ", fromCurrency='" + fromCurrency + '\'' +
                ", toCurrency='" + toCurrency + '\'' +
                ", rate=" + rate +
                ", date=" + date +
                '}';
    }
}
