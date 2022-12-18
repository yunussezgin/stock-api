package com.yunus.stockapi.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Currency;

@Entity
public class Stock {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotEmpty
    @Column(unique = true)
    private String productCode;

    private String name;

    private Currency currentPrice;

    private LocalDateTime lastUpdate;
}
