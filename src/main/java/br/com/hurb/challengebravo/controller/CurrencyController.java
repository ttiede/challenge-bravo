package br.com.hurb.challengebravo.controller;

import br.com.hurb.challengebravo.view.response.ExchangeCurrencyResponse;
import br.com.hurb.challengebravo.service.CurrenciesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/convert")
public class CurrencyController {

    final
    CurrenciesServices currenciesServices;

    public CurrencyController(CurrenciesServices currenciesServices) {
        this.currenciesServices = currenciesServices;
    }

    @GetMapping("/{origin}/{destiny}/{amountParam}")
    public ResponseEntity<ExchangeCurrencyResponse> exchangeCurrency(@PathVariable String origin,

                                                                     @PathVariable String destiny, @PathVariable String amountParam) {
        BigDecimal amount = new BigDecimal(amountParam);
        ExchangeCurrencyResponse result;
        result = currenciesServices.converting(origin, destiny, amount);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
