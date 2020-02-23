package com.searchmetrics.exchange.adapter.inbound.kafka;

import com.searchmetrics.exchange.interactor.GetExchangeRateInteractorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateConsumer {

    private static final Logger log = LoggerFactory.getLogger(ExchangeRateConsumer.class);

    private GetExchangeRateInteractorImpl interactor;

    public ExchangeRateConsumer(GetExchangeRateInteractorImpl interactor) {
        this.interactor = interactor;
    }

    @KafkaListener(topics = "exchange_rate_latest", groupId = "BTC_USD_latest")
    public void consumerExchangeRate(@Payload String message) {
        log.info("Message received on topic {} - {}", "exchange_rate_latest", message);
        interactor.saveExchangeRate(message);
    }
}
