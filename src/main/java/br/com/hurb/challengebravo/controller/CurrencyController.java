package br.com.hurb.challengebravo.controller;

import br.com.hurb.challengebravo.response.ResponseConvertedCurrency;
import br.com.hurb.challengebravo.service.CurrenciesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyController {

    @Autowired
    CurrenciesServices currenciesServices;

    @GetMapping("/convert/{origin}/{destiny}/{amount}")
    public ResponseEntity<ResponseConvertedCurrency> findStudentById(@PathVariable String origin,
                                                                     @PathVariable String destiny, @PathVariable BigDecimal amount) {
        ResponseConvertedCurrency result = currenciesServices.converting(origin, destiny, amount);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
