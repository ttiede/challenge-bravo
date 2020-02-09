package br.com.hurb.challengebravo.service;

import br.com.hurb.challengebravo.domain.BaseCurrency;
import br.com.hurb.challengebravo.domain.Currency;
import br.com.hurb.challengebravo.repository.BaseCurrencyRepository;
import br.com.hurb.challengebravo.response.CurrencyResponse;
import br.com.hurb.challengebravo.response.ResponseConvertedCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CurrenciesServices {
    @Autowired
    private BaseCurrencyRepository baseCurrencyRepository;
    @Value("${currency.base}")
    String baseCurrency;
    private static final Logger logger = LoggerFactory.getLogger(ResponseConvertedCurrency.class);

    @Cacheable("currency")
    public ResponseConvertedCurrency converting(String origin, String destiny, BigDecimal amount) {
        Optional<BaseCurrency> converting = baseCurrencyRepository.findById("Base");
        ResponseConvertedCurrency response = new ResponseConvertedCurrency();
        try {
            List<Currency> originList = converting.get().getCurrencies().stream()
                .filter(s -> origin.equals(s.getName()))
                .collect(Collectors.toList());

            List<Currency> destinyList = converting.get().getCurrencies().stream()
                .filter(s -> destiny.equals(s.getName()))
                .collect(Collectors.toList());


            Map<String, CurrencyResponse> original = new HashMap<>();
            original.put("original",
                new CurrencyResponse(
                    originList.get(0).getName()
                    ,
                    originList.get(0).getValue()
                )
            );
            Map<String, CurrencyResponse> result = new HashMap<>();
            result.put("result",
                new CurrencyResponse(
                    destinyList.get(0).getName()
                    ,
                    getConversionCurrencies(originList.get(0), destinyList.get(0), amount)
                )
            );
            response.setOriginal(original);
            response.setResult(result);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return response;
    }

    private BigDecimal getConversionCurrencies(Currency from, Currency to, BigDecimal amount) {
        if (from.getName().equals(this.baseCurrency)) return amount.multiply(to.getValue());
        if (to.getName().equals(this.baseCurrency)) return amount.divide(from.getValue());

        return amount.divide(from.getValue()).multiply(to.getValue());
    }
}