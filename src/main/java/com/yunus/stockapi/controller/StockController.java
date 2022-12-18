package com.yunus.stockapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yunus.stockapi.entity.Stock;
import com.yunus.stockapi.entity.StockUpdate;
import com.yunus.stockapi.service.StockService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/stocks")
    public ResponseEntity<List<Stock>> listStock(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size,
                                                 @SortDefault(sort = "name", direction = Sort.Direction.ASC)  Pageable pageable) {
        Page<Stock> stock =  stockService.listStock(pageable);
        return new ResponseEntity<>(stock.getContent(), HttpStatus.OK);
    }

    @PostMapping("/stocks")
    public ResponseEntity<Stock> createStock(@Valid @RequestBody Stock stock) {
        Stock stockSaved = stockService.createStock(stock);
        return new ResponseEntity<>(stockSaved, HttpStatus.CREATED);
    }

    @GetMapping("/stocks/{id}")
    public ResponseEntity<Stock> retrieveStock(@PathVariable("id") String id) {
        Stock stock = stockService.retrieveStock(id);
        return new ResponseEntity<>(stock, HttpStatus.OK);
    }

    @PatchMapping("/stocks/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable("id") String id,
                                             @Valid @RequestBody StockUpdate stock) throws JsonProcessingException {
        Stock stockUpdated = stockService.updateStock(id, stock);
        return new ResponseEntity<>(stockUpdated, HttpStatus.OK);
    }

    @DeleteMapping ("/stocks/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable("id") String id) {
        stockService.deleteStock(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
