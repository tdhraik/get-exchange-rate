package com.searchmetrics.exchange.controller.view.mapper;

import com.searchmetrics.exchange.aggregate.ExchangeRate;
import com.searchmetrics.exchange.aggregate.HistoricalExchangeRate;
import com.searchmetrics.exchange.controller.view.response.ExchangeRateResponse;
import com.searchmetrics.exchange.controller.view.response.HistoricalExchangeRateResponse;

import java.util.ArrayList;
import java.util.List;

public class ExchangeRateMapper {

    public static ExchangeRateResponse toExchangeRateResponse(ExchangeRate exchangeRate) {
        return ExchangeRateResponse.ExchangeRateResponseBuilder
                .anExchangeRateResponse()
                .withFromCurrency(exchangeRate.getFromCurrency())
                .withToCurrency(exchangeRate.getToCurrency())
                .withExchangeRate(exchangeRate.getRate())
                .withTimestamp(exchangeRate.getTimestamp())
                .build();
    }

    public static List<HistoricalExchangeRateResponse> toHistoricExchangeRateResponse(List<HistoricalExchangeRate> historicalRates) {
        List<HistoricalExchangeRateResponse> responses = new ArrayList<>();
        for(HistoricalExchangeRate historicalRate : historicalRates) {
            responses.add(toHistoricalExchangeRateResponse(historicalRate));
        }
        return responses;
    }

    private static HistoricalExchangeRateResponse toHistoricalExchangeRateResponse(HistoricalExchangeRate rate) {
        return HistoricalExchangeRateResponse.HistoricalExchangeRateResponseBuilder
                .aHistoricalExchangeRateResponse()
                .withFromCurrency(rate.getFromCurrency())
                .withToCurrency(rate.getToCurrency())
                .withExchangeRate(rate.getRate())
                .withDate(rate.getDate())
                .build();
    }
}
