package com.searchmetrics.exchange.interactor;

import com.searchmetrics.exchange.aggregate.ExchangeRate;
import com.searchmetrics.exchange.controller.view.response.ExchangeRateResponse;
import com.searchmetrics.exchange.controller.view.response.HistoricalExchangeRateResponse;

import java.time.LocalDate;
import java.util.List;

public interface GetExchangeRateInteractor {

    ExchangeRateResponse getLatestExchangeRate();
    ExchangeRate saveExchangeRate(String message);
    void saveHistoricalExchangeRate(String message);
    List<HistoricalExchangeRateResponse> getHistoricalRates(LocalDate from, LocalDate to);
}
