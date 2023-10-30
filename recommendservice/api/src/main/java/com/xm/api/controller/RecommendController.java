package com.xm.api.controller;

import com.xm.api.dto.StatDto;
import com.xm.api.dto.SymbolDto;
import com.xm.api.service.RecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/recommend")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Description("Recommend service")
public class RecommendController {
    private final RecommendService recommendService;

    @GetMapping("/monthstat")
    @Description("Exposes an endpoint that will return a descending sorted list of all the cryptos,\n" +
            " comparing the normalized range (i.e. (max-min)/min)")
    public List<StatDto> getMonthStat(@RequestParam(defaultValue = "true") Boolean approved,
                                      @RequestParam(required = false) LocalDate date) {
        return recommendService.getMonthStat(approved, date);
    }

    @GetMapping("/monthstat/{symbol}")
    @Description("Exposes an endpoint that will return the oldest/newest/min/max values for a requested\n" +
            " crypto")
    public StatDto getMonthStatPerSymbol(@PathVariable(name = "symbol") String symbol,
                                         @RequestParam(required = false) LocalDate date) {
        return recommendService.getMonthStatPerSymbol(symbol, date);
    }

    @GetMapping("/daystat")
    @Description("Exposes an endpoint that will return the crypto with the highest normalized range for a\n" +
            " specific day")
    public StatDto getBestDailyStat(@RequestParam(defaultValue = "true") Boolean approved,
                                    @RequestParam(required = false) LocalDate date) {
        return recommendService.getBestDailyStat(approved, date);
    }

    @GetMapping("/symbols")
    @Description("Exposes an endpoint that will return the crypto list")
    public List<SymbolDto> getBestDailyStat() {
        return recommendService.getCryptoList();
    }
}
