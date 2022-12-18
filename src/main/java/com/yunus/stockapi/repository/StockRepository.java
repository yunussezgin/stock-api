package com.yunus.stockapi.repository;

import com.yunus.stockapi.entity.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends CrudRepository<Stock, String> {
}
