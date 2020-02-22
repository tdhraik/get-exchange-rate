package com.searchmetrics.exchange.adapter.outbound.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GenerateExchangeRateClientImpl implements GenerateExchangeRateClient {

    @Value("${generate.exchange.rate.latest.url}")
    private String generateExchangeRateUrl;

    @Value("${generate.exchange.rate.historical.url}")
    private String generateHistoricalExchangeRateUrl;

    private RestTemplate restTemplate;

    public GenerateExchangeRateClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getLatestRate() {
        ResponseEntity<String> latestRate = restTemplate.getForEntity(generateExchangeRateUrl, String.class);
        return latestRate.getBody();
    }

    @Override
    public String populateHistoricalRates() {
        ResponseEntity<String> historicalRates = restTemplate.getForEntity(generateHistoricalExchangeRateUrl, String.class);
        return historicalRates.getBody();
    }
}
