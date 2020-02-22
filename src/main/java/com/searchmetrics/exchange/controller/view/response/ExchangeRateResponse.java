package com.searchmetrics.exchange.controller.view.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateResponse {

    public String fromCurrency;

    public String toCurrency;

    public BigDecimal exchangeRate;

    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Berlin")
    public LocalDateTime timestamp;

    public static final class ExchangeRateResponseBuilder {
        public String fromCurrency;
        public String toCurrency;
        public BigDecimal exchangeRate;
        public LocalDateTime timestamp;

        private ExchangeRateResponseBuilder() {
        }

        public static ExchangeRateResponseBuilder anExchangeRateResponse() {
            return new ExchangeRateResponseBuilder();
        }

        public ExchangeRateResponseBuilder withFromCurrency(String fromCurrency) {
            this.fromCurrency = fromCurrency;
            return this;
        }

        public ExchangeRateResponseBuilder withToCurrency(String toCurrency) {
            this.toCurrency = toCurrency;
            return this;
        }

        public ExchangeRateResponseBuilder withExchangeRate(BigDecimal exchangeRate) {
            this.exchangeRate = exchangeRate;
            return this;
        }

        public ExchangeRateResponseBuilder withTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ExchangeRateResponse build() {
            ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse();
            exchangeRateResponse.setFromCurrency(fromCurrency);
            exchangeRateResponse.setToCurrency(toCurrency);
            exchangeRateResponse.setExchangeRate(exchangeRate);
            exchangeRateResponse.setTimestamp(timestamp);
            return exchangeRateResponse;
        }
    }
}
