package com.searchmetrics.exchange.adapter.outbound.repository;

import com.searchmetrics.exchange.aggregate.HistoricalExchangeRate;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HistoricalExchangeRateRepository extends MongoRepository<HistoricalExchangeRate, String> {

    @Query("{ 'date' : { $gte: ?0 } , { $lte: ?1 } }")
    List<HistoricalExchangeRate> findByDateRange(LocalDate from, LocalDate to, Sort sortOrder);

    default List<HistoricalExchangeRate> findByDateRangeSorted(LocalDate from, LocalDate to) {
        return findByDateRange(from, to, Sort.by("date").descending());
    }
}
