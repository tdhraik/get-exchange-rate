package com.searchmetrics.exchange.interactor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.searchmetrics.exchange.adapter.inbound.kafka.EnrichedExchangeRateMsg;
import com.searchmetrics.exchange.adapter.outbound.repository.ExchangeRateRepository;
import com.searchmetrics.exchange.adapter.outbound.client.GenerateExchangeRateClient;
import com.searchmetrics.exchange.adapter.outbound.repository.HistoricalExchangeRateRepository;
import com.searchmetrics.exchange.aggregate.ExchangeRate;
import com.searchmetrics.exchange.aggregate.HistoricalExchangeRate;
import com.searchmetrics.exchange.controller.view.mapper.ExchangeRateMapper;
import com.searchmetrics.exchange.controller.view.response.ExchangeRateResponse;
import com.searchmetrics.exchange.controller.view.response.HistoricalExchangeRateResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetExchangeRateInteractorImpl implements GetExchangeRateInteractor {

    private ExchangeRateRepository latestRateRepository;

    private HistoricalExchangeRateRepository historicalRateRepository;

    private GenerateExchangeRateClient generateExchangeClient;

    private ObjectMapper mapper;

    public GetExchangeRateInteractorImpl(ExchangeRateRepository latestRateRepository,
                                         HistoricalExchangeRateRepository historicalRateRepository,
                                         GenerateExchangeRateClient generateExchangeClient, ObjectMapper mapper) {
        this.latestRateRepository = latestRateRepository;
        this.historicalRateRepository = historicalRateRepository;
        this.generateExchangeClient = generateExchangeClient;
        this.mapper = mapper;
    }

    public ExchangeRateResponse getLatestExchangeRate() {
        ExchangeRate exchangeRate = latestRateRepository.getLatestExchangeRate(LocalDate.now().atStartOfDay())
                .orElseGet(() -> {
                    String response = generateExchangeClient.getLatestRate();
                    return toExchangeRate(toEnrichedExchangeRateMsg(response));
                });
        return ExchangeRateMapper.toExchangeRateResponse(exchangeRate);
    }

    public ExchangeRate saveExchangeRate(String message) {
        EnrichedExchangeRateMsg msg = toEnrichedExchangeRateMsg(message);
        return latestRateRepository.save(toExchangeRate(msg));
    }

    private ExchangeRate toExchangeRate(EnrichedExchangeRateMsg message) {
        return EnrichedExchangeRateMsg.toExchangeRate(message);
    }

    private EnrichedExchangeRateMsg toEnrichedExchangeRateMsg(String message) {
        EnrichedExchangeRateMsg enrichedMsg = null;
        try {
            enrichedMsg = mapper.readValue(message, EnrichedExchangeRateMsg.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return enrichedMsg;
    }

    public void saveHistoricalExchangeRate(String message) {
        EnrichedExchangeRateMsg msg = toEnrichedExchangeRateMsg(message);
        historicalRateRepository.save(toHistoricalExchangeRate(msg));
    }

    private HistoricalExchangeRate toHistoricalExchangeRate(EnrichedExchangeRateMsg message) {
        return EnrichedExchangeRateMsg.toHistoricalExchangeRate(message);
    }

    public List<HistoricalExchangeRateResponse> getHistoricalRates(LocalDate from, LocalDate to) {
        List<HistoricalExchangeRate> historicalRates = historicalRateRepository.findByDateRangeSorted(from, to);
        if(historicalRates.isEmpty()) {
            generateExchangeClient.populateHistoricalRates();
            return new ArrayList<>(); // will consume msgs through kafka consumer
        }
        return ExchangeRateMapper.toHistoricExchangeRateResponse(historicalRates);
    }
}
