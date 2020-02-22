package com.searchmetrics.exchange.controller;

import com.searchmetrics.exchange.controller.view.response.ExchangeRateResponse;
import com.searchmetrics.exchange.controller.view.response.HistoricalExchangeRateResponse;
import com.searchmetrics.exchange.interactor.GetExchangeRateInteractorImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/exchange")
public class GetExchangeRateController {

    private GetExchangeRateInteractorImpl interactor;

    public GetExchangeRateController(GetExchangeRateInteractorImpl interactor) {
        this.interactor = interactor;
    }

    @GetMapping(value = "/latest", produces = MediaType.APPLICATION_JSON_VALUE)
    public ExchangeRateResponse getExchangeRate() {
        return interactor.getLatestExchangeRate();
    }

    @GetMapping("/historic")
    public List<HistoricalExchangeRateResponse> getHistoricExchangeRate(@RequestParam("from") String from,
                                                                        @RequestParam("to") String to) {
        LocalDate fromDate = LocalDate.parse(from, DateTimeFormatter.ISO_DATE);
        LocalDate toDate = LocalDate.parse(to, DateTimeFormatter.ISO_DATE);
        return interactor.getHistoricalRates(fromDate, toDate);
    }
}
