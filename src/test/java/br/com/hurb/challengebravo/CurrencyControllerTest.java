package br.com.hurb.challengebravo;

import br.com.hurb.challengebravo.config.DataLoaderInit;
import br.com.hurb.challengebravo.config.RedisConfiguration;
import br.com.hurb.challengebravo.config.RedisProperties;
import br.com.hurb.challengebravo.config.ResourcesConfig;
import br.com.hurb.challengebravo.error.CurrenciesServicesException;
import br.com.hurb.challengebravo.model.domain.BaseCurrency;
import br.com.hurb.challengebravo.model.domain.Currency;
import br.com.hurb.challengebravo.model.repository.BaseCurrencyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"spring.redis.port=6376"})
public class CurrencyControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BaseCurrencyRepository baseCurrencyRepository;

    @Before
    public void populateDataBase() {
        List<Currency> currencies = new ArrayList<>();
        currencies.add(new Currency("BRL", "BRL", BigDecimal.TEN));
        currencies.add(new Currency("USD", "USD", BigDecimal.TEN));
        BaseCurrency baseCurrency = new BaseCurrency("Base", "USD", currencies);
        BaseCurrency saved = baseCurrencyRepository.save(baseCurrency);
    }

    @Test
    public void exchangeCurrencyWithSuccess()
        throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/convert/USD/BRL/1",
            String.class)).contains("{\"original\":{\"currency\":\"USD\",\"amount\":10},\"result\":{\"currency\":\"BRL\",\"amount\":10}}");
    }

    @Test
    public void exchangeCurrencyWithNotFound()
        throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/convert/USD/EUR/1",
            String.class)).contains("Currency not found");
    }
}
