package com.searchmetrics.exchange.adapter.outbound.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Service
public class GenerateExchangeRateClientImpl implements GenerateExchangeRateClient {

    @Value("${generate.exchange.rate.latest.url}")
    private String generateExchangeRateUrl;

    @Value("${generate.exchange.rate.historical.url}")
    private String generateHistoricalExchangeRateUrl;

    @Value("${generate.service.actuator.refresh}")
    private String actuatorRefresh;

    @Value("${generate.service.scheduler.refresh}")
    private String schedulerRefresh;

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

    @Override
    public void refreshConfigurations() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(new HashMap<>(), headers);
        restTemplate.postForEntity(actuatorRefresh, request, Void.class);
        restTemplate.getForEntity(schedulerRefresh, String.class);
    }
}
