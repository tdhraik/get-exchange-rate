package com.searchmetrics.exchange.adapter.inbound.kafka;

import com.searchmetrics.exchange.interactor.GetExchangeRateInteractorImpl;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateConsumer {

    private GetExchangeRateInteractorImpl interactor;

    public ExchangeRateConsumer(GetExchangeRateInteractorImpl interactor) {
        this.interactor = interactor;
    }

    @KafkaListener(topics = "exchange_rate_latest", groupId = "BTC_USD_latest")
    public void consumerExchangeRate(@Payload String message) {
        System.out.println(String.format("Message received on topic {%s} - {%s}", "exchange_rate_latest", message));
        interactor.saveExchangeRate(message);
    }
}
