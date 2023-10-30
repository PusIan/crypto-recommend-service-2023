package com.xm.api.repository;

import com.xm.api.model.Symbol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SymbolRepository extends JpaRepository<Symbol, Long> {
    List<Symbol> findAll();
}
