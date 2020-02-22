package com.searchmetrics.exchange.adapter.inbound.kafka;

import com.searchmetrics.exchange.interactor.GetExchangeRateInteractorImpl;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class HistoricalExchangeRateConsumer {

    private GetExchangeRateInteractorImpl interactor;

    public HistoricalExchangeRateConsumer(GetExchangeRateInteractorImpl interactor) {
        this.interactor = interactor;
    }

    @KafkaListener(topics = "exchange_rate_historic", groupId = "BTC_USD_historical")
    public void consumerHistoricalExchangeRate(@Payload String message) {
        System.out.println(String.format("Message received on topic {%s} - {%s}", "exchange_rate_historic", message));
        interactor.saveHistoricalExchangeRate(message);
    }
}
