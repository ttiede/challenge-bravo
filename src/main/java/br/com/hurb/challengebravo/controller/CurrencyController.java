package br.com.hurb.challengebravo.controller;

import br.com.hurb.challengebravo.exception.CurrenciesNotFoundException;
import br.com.hurb.challengebravo.exception.CurrenciesServicesException;
import br.com.hurb.challengebravo.response.ResponseConvertedCurrency;
import br.com.hurb.challengebravo.service.CurrenciesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class CurrencyController {

    @Autowired
    CurrenciesServices currenciesServices;

    @GetMapping("/convert/{origin}/{destiny}/{amount}")
    public ResponseEntity<ResponseConvertedCurrency> findStudentById(@PathVariable String origin,

                                                                     @PathVariable String destiny, @PathVariable BigDecimal amount) {
        ResponseConvertedCurrency result;
        try {
            result = currenciesServices.converting(origin, destiny, amount);
        } catch (CurrenciesServicesException ex) {
            return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (CurrenciesNotFoundException ex) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
