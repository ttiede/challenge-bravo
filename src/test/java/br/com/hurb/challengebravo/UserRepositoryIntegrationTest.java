package br.com.hurb.challengebravo;

import br.com.hurb.challengebravo.config.DataLoaderInit;
import br.com.hurb.challengebravo.config.RedisConfiguration;
import br.com.hurb.challengebravo.config.RedisProperties;
import br.com.hurb.challengebravo.config.ResourcesConfig;
import br.com.hurb.challengebravo.model.domain.BaseCurrency;
import br.com.hurb.challengebravo.model.domain.Currency;
import br.com.hurb.challengebravo.model.repository.BaseCurrencyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RedisProperties.class, RedisConfiguration.class, DataLoaderInit.class, ResourcesConfig.class})
@EnableAutoConfiguration
public class UserRepositoryIntegrationTest {
    @Autowired
    private BaseCurrencyRepository baseCurrencyRepository;

    @Test
    public void shouldSaveBase_toRedis() {
        List<Currency> currencies = new ArrayList<>();
        currencies.add(new Currency("BRL", "BRL", BigDecimal.TEN));
        BaseCurrency baseCurrency = new BaseCurrency("Base", "USD", currencies);

        BaseCurrency saved = baseCurrencyRepository.save(baseCurrency);

        assertNotNull(saved);
    }
}
