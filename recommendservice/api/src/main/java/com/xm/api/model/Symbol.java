package com.xm.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "symbols")
@Getter
@Setter
public class Symbol {
    @Id
    @Column(name = "symbol")
    private String symbol;
    @Column(name = "approved")
    private boolean approved;
}
