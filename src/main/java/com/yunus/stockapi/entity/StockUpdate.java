package com.yunus.stockapi.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockUpdate extends SubEntity {

    private String productCode;

    private String name;

    private BigDecimal currentPrice;

}