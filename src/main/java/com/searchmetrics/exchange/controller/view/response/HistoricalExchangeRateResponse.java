package com.searchmetrics.exchange.controller.view.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoricalExchangeRateResponse {

    public String fromCurrency;

    public String toCurrency;

    public BigDecimal exchangeRate;

    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Berlin")
    public LocalDate date;

    public static final class HistoricalExchangeRateResponseBuilder {
        public String fromCurrency;
        public String toCurrency;
        public BigDecimal exchangeRate;
        public LocalDate date;

        private HistoricalExchangeRateResponseBuilder() {
        }

        public static HistoricalExchangeRateResponseBuilder aHistoricalExchangeRateResponse() {
            return new HistoricalExchangeRateResponseBuilder();
        }

        public HistoricalExchangeRateResponseBuilder withFromCurrency(String fromCurrency) {
            this.fromCurrency = fromCurrency;
            return this;
        }

        public HistoricalExchangeRateResponseBuilder withToCurrency(String toCurrency) {
            this.toCurrency = toCurrency;
            return this;
        }

        public HistoricalExchangeRateResponseBuilder withExchangeRate(BigDecimal exchangeRate) {
            this.exchangeRate = exchangeRate;
            return this;
        }

        public HistoricalExchangeRateResponseBuilder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public HistoricalExchangeRateResponse build() {
            HistoricalExchangeRateResponse historicalExchangeRateResponse = new HistoricalExchangeRateResponse();
            historicalExchangeRateResponse.setFromCurrency(fromCurrency);
            historicalExchangeRateResponse.setToCurrency(toCurrency);
            historicalExchangeRateResponse.setExchangeRate(exchangeRate);
            historicalExchangeRateResponse.setDate(date);
            return historicalExchangeRateResponse;
        }
    }
}
