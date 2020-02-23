package com.searchmetrics.exchange.interactor;

import com.searchmetrics.exchange.adapter.outbound.client.GenerateExchangeRateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminInteractor {

    private final GenerateExchangeRateClient client;

    public AdminInteractor(GenerateExchangeRateClient client) {
        this.client = client;
    }

    public void refreshConfigurations() {
        client.refreshConfigurations();
    }
}
