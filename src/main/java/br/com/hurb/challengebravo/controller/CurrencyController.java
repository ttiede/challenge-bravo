package br.com.hurb.challengebravo.controller;

import br.com.hurb.challengebravo.domain.Currency;
import br.com.hurb.challengebravo.service.CurrenciesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {

    @Autowired
    CurrenciesServices currenciesServices;

    @GetMapping("/student/{id}")
    public Currency findStudentById(@PathVariable String id) {
        System.out.println("Searching by ID  : " + id);

        return currenciesServices.getById(id);
    }
}
