package com.yunus.stockapi.controller;

import com.yunus.stockapi.entity.Stock;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stocks")
public class StockController {

    @GetMapping
    public ResponseEntity<Stock> getStock() {
        return null;
    }

    @PostMapping
    public ResponseEntity<Stock> saveStock() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> retrieveStock(@PathVariable String id) {
        return null;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable String id) {
        return null;
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Stock> deleteStock(@PathVariable String id) {
        return null;
    }

}
