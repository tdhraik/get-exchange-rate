package com.searchmetrics.exchange.adapter.outbound.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GenerateExchangeRateClientImpl implements GenerateExchangeRateClient {

    @Value("${generate.exchange.rate.latest.url}")
    private String generateExchangeRateUrl;

    private RestTemplate restTemplate;

    public GenerateExchangeRateClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getLatestRate() {
        ResponseEntity<String> latestRate = restTemplate.getForEntity(generateExchangeRateUrl, String.class);
        return latestRate.getBody();
    }
}
