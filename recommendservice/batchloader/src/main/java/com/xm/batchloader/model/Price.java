package com.xm.batchloader.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "prices",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"dt", "symbol"})})
@Getter
@Setter
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "dt")
    private LocalDateTime dt;
    @Column(name = "symbol")
    private String symbol;
    @Column(name = "price")
    private Double price;
}
