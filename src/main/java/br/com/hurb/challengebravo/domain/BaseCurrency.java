package br.com.hurb.challengebravo.domain;


import lombok.Data;

import java.util.List;

@Data
public class BaseCurrency {

    String id;
    String name;
    String  value;
    protected List<Currency> currencies;

    public BaseCurrency(String id, String name, String value, List<Currency> currencies) {
        super();
        this.id = id;
        this.name = name;
        this.value = value;
        this.currencies = currencies;
    }

}
