package br.com.hurb.challengebravo.service;

import br.com.hurb.challengebravo.model.dto.CurrencyDto;
import org.springframework.web.client.RestTemplate;

public class CurrencyExternalService {
    private final RestTemplate restTemplate;

    public CurrencyExternalService() {
        this.restTemplate = new RestTemplate();
    }

    public CurrencyDto listCurrencies(String url) {
        return this.restTemplate.getForObject(url, CurrencyDto.class);
    }
}
