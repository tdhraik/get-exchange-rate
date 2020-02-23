package com.searchmetrics.exchange.adapter.outbound.client;

public interface GenerateExchangeRateClient {

    String getLatestRate();

    String populateHistoricalRates();

    void refreshConfigurations();
}
