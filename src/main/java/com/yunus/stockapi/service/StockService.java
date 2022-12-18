package com.yunus.stockapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yunus.stockapi.entity.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface StockService {

    Page<Stock> listStock(Pageable page);

    Stock retrieveStock(String id);

    Stock createStock(Stock stock);

    Stock updateStock(String id, Stock stock) throws JsonProcessingException;

    void deleteStock(String id);

}
