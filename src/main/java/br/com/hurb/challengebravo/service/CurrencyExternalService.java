package br.com.hurb.challengebravo.service;

import br.com.hurb.challengebravo.DTO.CurrencyDTO;
import org.springframework.web.client.RestTemplate;

public class CurrencyExternalService {
    private final RestTemplate restTemplate;

    public CurrencyExternalService() {
        this.restTemplate = new RestTemplate();
    }

    public CurrencyDTO listCurrencies(String url) {
        return this.restTemplate.getForObject(url, CurrencyDTO.class);
    }
}
