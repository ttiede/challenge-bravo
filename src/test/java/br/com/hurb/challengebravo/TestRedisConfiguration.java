package br.com.hurb.challengebravo;

import br.com.hurb.challengebravo.config.RedisProperties;
import br.com.hurb.challengebravo.domain.Currency;
import br.com.hurb.challengebravo.repository.CurrencyRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;

@TestConfiguration
public class TestRedisConfiguration {

    private RedisServer redisServer;

    public TestRedisConfiguration(RedisProperties redisProperties) {
        this.redisServer = new RedisServer(redisProperties.getRedisPort());
    }

    @PostConstruct
    public void postConstruct() {
        redisServer.start();
    }

    @PreDestroy
    public void preDestroy() {
        redisServer.stop();
    }
}

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestRedisConfiguration.class)
class CurrencyRepositoryIntegrationTest {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Test
    public void shouldSaveCurrency_toRedis() {
        UUID id = UUID.randomUUID();
        BigDecimal value = new BigDecimal(1);
        Currency currency = new Currency(id.toString(), "name", value);

        Currency saved = currencyRepository.save(currency);
        assertNotNull(saved);
        List<Currency> search = (List<Currency>) currencyRepository.findAll();
        Optional<Currency> search2 = currencyRepository.findById(id.toString());
        assertNotNull(saved);

    }
}