package com.searchmetrics.exchange.adapter.outbound.repository;

import com.searchmetrics.exchange.aggregate.ExchangeRate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends MongoRepository<ExchangeRate, String> {

    @Query("{ 'timestamp' : { $gt: ?0 } }")
    Page<ExchangeRate> findLatestExchangeRate(LocalDateTime timestamp, Pageable page);

    default Optional<ExchangeRate> getLatestExchangeRate(LocalDateTime timestamp) {
        PageRequest request = PageRequest.of(0,1, Sort.by("timestamp").descending());
        List<ExchangeRate> exchangeRates = findLatestExchangeRate(timestamp, request).getContent();
        if(exchangeRates.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(exchangeRates.get(0));
    }
}