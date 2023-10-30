package com.xm.api.mapper;

import com.xm.api.dto.SymbolDto;
import com.xm.api.model.Symbol;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SymbolMapper {
    SymbolDto toSymbolDto(Symbol symbol);

    List<SymbolDto> toSymbolDtos(List<Symbol> symbol);
}
