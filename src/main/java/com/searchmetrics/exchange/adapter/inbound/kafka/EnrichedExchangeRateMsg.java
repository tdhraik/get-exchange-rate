package com.searchmetrics.exchange.adapter.inbound.kafka;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.searchmetrics.exchange.aggregate.ExchangeRate;
import com.searchmetrics.exchange.aggregate.HistoricalExchangeRate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnrichedExchangeRateMsg {

    private String fromCurrency;
    private String toCurrency;
    private BigDecimal rate;
    private long timestamp;

    public static ExchangeRate toExchangeRate(EnrichedExchangeRateMsg msg) {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setToCurrency(msg.getToCurrency());
        exchangeRate.setFromCurrency(msg.getFromCurrency());
        exchangeRate.setRate(msg.getRate());
        exchangeRate.setTimestamp(LocalDateTime.ofEpochSecond(
                msg.getTimestamp(), 0, ZoneOffset.UTC));
        return exchangeRate;
    }

    public static HistoricalExchangeRate toHistoricalExchangeRate(EnrichedExchangeRateMsg msg) {
        HistoricalExchangeRate exchangeRate = new HistoricalExchangeRate();
        exchangeRate.setToCurrency(msg.getToCurrency());
        exchangeRate.setFromCurrency(msg.getFromCurrency());
        exchangeRate.setRate(msg.getRate());
        exchangeRate.setDate(LocalDateTime.ofEpochSecond(
                msg.getTimestamp(), 0, ZoneOffset.UTC).toLocalDate());
        return exchangeRate;
    }
}
