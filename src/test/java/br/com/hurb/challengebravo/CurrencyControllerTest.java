package br.com.hurb.challengebravo;

import br.com.hurb.challengebravo.config.DataLoaderInit;
import br.com.hurb.challengebravo.config.RedisConfiguration;
import br.com.hurb.challengebravo.config.RedisProperties;
import br.com.hurb.challengebravo.config.ResourcesConfig;
import br.com.hurb.challengebravo.controller.CurrencyController;
import br.com.hurb.challengebravo.controller.CustomExceptionHandler;
import br.com.hurb.challengebravo.error.CurrenciesServicesException;
import br.com.hurb.challengebravo.model.domain.BaseCurrency;
import br.com.hurb.challengebravo.model.domain.Currency;
import br.com.hurb.challengebravo.model.repository.BaseCurrencyRepository;
import br.com.hurb.challengebravo.service.CurrenciesServices;
import br.com.hurb.challengebravo.view.response.ExchangeCurrencyResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.BIG_DECIMAL;
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

    @Mock
    CurrencyController currencyController;

    @Autowired
    CurrenciesServices currenciesServices;

    private MockMvc mockMvc;

    @Before
    public void populateDataBase() {
        List<Currency> currencies = new ArrayList<>();
        BigDecimal valueCurrency = BigDecimal.TEN;
        currencies.add(new Currency("BRL", valueCurrency));
        currencies.add(new Currency("BRL", valueCurrency));
        currencies.add(new Currency("EUR", valueCurrency));
        currencies.add(new Currency("BTC", valueCurrency));
        currencies.add(new Currency("ETH", valueCurrency));
        currencies.add(new Currency("USD", BigDecimal.ONE));

        BaseCurrency baseCurrency = new BaseCurrency("Base", currencies);
        BaseCurrency saved = baseCurrencyRepository.save(baseCurrency);
    }

    @Test
    public void exchangeCurrencyWithSuccess()
        throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/convert/USD/BRL/1",
            String.class)).contains("{\"original\":{\"currency\":\"USD\",\"amount\":1},\"result\":{\"currency\":\"BRL\",\"amount\":10}}");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/convert/EUR/BRL/1",
            String.class)).contains("{\"original\":{\"currency\":\"EUR\",\"amount\":1},\"result\":{\"currency\":\"BRL\",\"amount\":10}}");
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/convert/BRL/USD/10",
            String.class)).contains("{\"original\":{\"currency\":\"BRL\",\"amount\":10},\"result\":{\"currency\":\"USD\",\"amount\":1}}");
    }

    @Test
    public void exchangeCurrencyBase()
        throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/convert/USD/USD/10",
            String.class)).contains("{\"original\":{\"currency\":\"USD\",\"amount\":10},\"result\":{\"currency\":\"USD\",\"amount\":10}}");
    }

    @Test
    public void exchangeCurrencyAmountZero()
        throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/convert/USD/USD/0",
            String.class)).contains("Currency services has a error");
    }

    @Test
    public void exchangeCurrencyWithNotFound()
        throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/convert/YEN/EUR/1",
            String.class)).contains("Currency not found");
    }
}

