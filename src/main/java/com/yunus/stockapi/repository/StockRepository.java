package com.yunus.stockapi.repository;

import com.yunus.stockapi.entity.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends PagingAndSortingRepository<Stock, String> {

}
