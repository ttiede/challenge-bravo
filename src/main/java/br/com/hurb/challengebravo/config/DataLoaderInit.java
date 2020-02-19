package br.com.hurb.challengebravo.config;

import br.com.hurb.challengebravo.model.dto.CurrencyDto;
import br.com.hurb.challengebravo.model.domain.BaseCurrency;
import br.com.hurb.challengebravo.model.domain.Currency;
import br.com.hurb.challengebravo.model.repository.BaseCurrencyRepository;
import br.com.hurb.challengebravo.service.CurrencyExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DataLoaderInit implements ApplicationRunner {
    @Autowired
    private BaseCurrencyRepository baseCurrencyRepository;

    @Value("${currency.list.init}")
    String listCurrenciesInit;
    @Value("${currency.external.init}")
    String urlExternalCurrencies;
    @Value("${currency.base}")
    String baseCurrency;


    @Override
    public void run(ApplicationArguments args) {
        CurrencyExternalService currencyExternalService = new CurrencyExternalService();

        CurrencyDto response = currencyExternalService.listCurrencies(urlExternalCurrencies + listCurrenciesInit);

        PopulateCurrenciesInit(response);
        System.out.println("carregado os dados");

    }

    private void PopulateCurrenciesInit(CurrencyDto response) {
        List<Currency> currencies = new ArrayList<>();
        currencies.add(new Currency("BRL", response.getBRL()));
        currencies.add(new Currency("EUR", response.getEUR()));
        currencies.add(new Currency("BTC", response.getBTC()));
        currencies.add(new Currency("ETH", response.getETH()));
        currencies.add(new Currency("USD", response.getUSD()));

        BaseCurrency baseCurrency = new BaseCurrency("Base", currencies);

        BaseCurrency saved = baseCurrencyRepository.save(baseCurrency);
        Optional<BaseCurrency> search = baseCurrencyRepository.findById(saved.getId());
    }
}