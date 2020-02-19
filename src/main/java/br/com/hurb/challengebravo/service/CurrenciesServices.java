package br.com.hurb.challengebravo.service;

import br.com.hurb.challengebravo.error.CurrenciesNotFoundException;
import br.com.hurb.challengebravo.error.CurrenciesServicesException;
import br.com.hurb.challengebravo.model.domain.BaseCurrency;
import br.com.hurb.challengebravo.model.domain.Currency;
import br.com.hurb.challengebravo.model.repository.BaseCurrencyRepository;
import br.com.hurb.challengebravo.view.response.CurrencyResponse;
import br.com.hurb.challengebravo.view.response.ExchangeCurrencyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Optional;

@Service
public class CurrenciesServices {
    private final BaseCurrencyRepository baseCurrencyRepository;
    @Value("${currency.base}")
    String baseCurrency;
    private static final Logger logger = LoggerFactory.getLogger(ExchangeCurrencyResponse.class);

    public CurrenciesServices(BaseCurrencyRepository baseCurrencyRepository) {
        this.baseCurrencyRepository = baseCurrencyRepository;
    }

    @Cacheable("currency")
    public ExchangeCurrencyResponse converting(String originParam, String destinyParams, BigDecimal amountParams) {
        try {
            if (amountParams.compareTo(BigDecimal.ZERO) <= 0)
                throw new CurrenciesServicesException("Amount is invalid");

            Optional<BaseCurrency> converting = baseCurrencyRepository.findById("Base");
            Currency originValue = Collections.unmodifiableList(converting.get().getCurrencies()).stream().filter(currency -> originParam.equals(currency.getId())).findFirst().orElse(null);
            Currency destinyValue = Collections.unmodifiableList(converting.get().getCurrencies()).stream().filter(s -> destinyParams.equals(s.getId())).findFirst().orElse(null);

            if (destinyValue == null || originValue == null) {
                throw new CurrenciesNotFoundException("Currencies not found");
            }

            ExchangeCurrencyResponse result = new ExchangeCurrencyResponse();
            result.setOriginal(new CurrencyResponse(originParam, amountParams));

            result.setResult(new CurrencyResponse(destinyParams, calcConversionCurrencies(originValue, destinyValue, amountParams)));

            return result;
        } catch (
            CurrenciesNotFoundException e) {
            logger.error(e.getMessage());
            throw new CurrenciesNotFoundException(e.getMessage());
        } catch (
            Throwable e) {
            logger.error(e.getMessage());
            throw new CurrenciesServicesException(e.getMessage());
        }

    }

    public BigDecimal calcConversionCurrencies(Currency from, Currency to, BigDecimal amount) {
        BigDecimal[] valor = amount.divideAndRemainder(from.getValue());
        BigDecimal divididValue = valor[0].add(valor[1]);

        if (from.getId().equals(to.getId())) return amount;
        if (from.getId().equals(this.baseCurrency))
            return to.getValue().multiply(amount);
        if (to.getId().equals(this.baseCurrency)) {
            return divididValue;
        }

        return divididValue.multiply(to.getValue());

    }
}
