package com.yunus.stockapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunus.stockapi.entity.Stock;
import com.yunus.stockapi.entity.StockUpdate;
import com.yunus.stockapi.exception.NotFoundException;
import com.yunus.stockapi.repository.StockRepository;
import com.yunus.stockapi.util.Constants;
import com.yunus.stockapi.util.JsonMergePatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    private final ObjectMapper objectMapper;
    private final JsonMergePatcher jsonMergePatcher;

    public StockServiceImpl(StockRepository stockRepository, ObjectMapper objectMapper, JsonMergePatcher jsonMergePatcher) {
        this.stockRepository = stockRepository;
        this.objectMapper = objectMapper;
        this.jsonMergePatcher = jsonMergePatcher;
    }

    @Override
    public Page<Stock> listStock(Pageable page) {
        return stockRepository.findAll(page);
    }

    @Override
    public Stock retrieveStock(String id) {
        return stockRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.NOT_FOUND_EXCEPTION_MESSAGE, id)));
    }

    @Override
    public Stock createStock(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public Stock updateStock(String id, StockUpdate stock) throws JsonProcessingException {
        Stock stockOld = stockRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(Constants.NOT_FOUND_EXCEPTION_MESSAGE, id)));
        String serializedBody = objectMapper.writeValueAsString(stock);
        Stock stockMerged = jsonMergePatcher.mergePatch(serializedBody, stockOld);
        return stockRepository.save(stockMerged);
    }

    @Override
    public void deleteStock(String id) {
        stockRepository.delete(retrieveStock(id));
    }
}
