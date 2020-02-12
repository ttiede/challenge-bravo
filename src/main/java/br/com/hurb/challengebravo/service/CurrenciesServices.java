package br.com.hurb.challengebravo.service;

import br.com.hurb.challengebravo.exception.CurrenciesNotFoundException;
import br.com.hurb.challengebravo.exception.CurrenciesServicesException;
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
import java.util.Optional;

@Service
public class CurrenciesServices {
    @Autowired
    private BaseCurrencyRepository baseCurrencyRepository;
    @Value("${currency.base}")
    String baseCurrency;
    private static final Logger logger = LoggerFactory.getLogger(ResponseConvertedCurrency.class);

    @Cacheable("currency")
    public ResponseConvertedCurrency converting(String originParam, String destinyParams, BigDecimal amountParams) {
        Optional<BaseCurrency> converting = baseCurrencyRepository.findById("Base");
        try {
            Currency originValue = converting.get().getCurrencies().stream()
                .filter(s -> originParam.equals(s.getName()))
                .findFirst().orElse(null);

            Currency destinyValue = converting.get().getCurrencies().stream()
                .filter(s -> destinyParams.equals(s.getName()))
                .findFirst().orElse(null);

            if (destinyValue == null || originValue == null) {
                throw new CurrenciesNotFoundException("Currencies not found");
            }

            ResponseConvertedCurrency result = new ResponseConvertedCurrency();
            result.setOriginal(new CurrencyResponse(originParam, originValue.getValue()));

            result.setResult(new CurrencyResponse(destinyParams, getConversionCurrencies(originValue, destinyValue, amountParams)));

            return result;
        } catch (CurrenciesNotFoundException e) {
            logger.error(e.getMessage());
            throw new CurrenciesNotFoundException(e.getMessage());
        } catch (Throwable e) {
            logger.error(e.getMessage());
            throw new CurrenciesServicesException(e.getMessage());
        }
    }

    private BigDecimal getConversionCurrencies(Currency from, Currency to, BigDecimal amount) {
        if(amount.compareTo(BigDecimal.ZERO) == 0) return amount;
        if (from.getName().equals(this.baseCurrency)) return amount.multiply(to.getValue());
        if (to.getName().equals(this.baseCurrency)) return amount.divide(from.getValue());

        return amount.divide(from.getValue()).multiply(to.getValue());
    }
}