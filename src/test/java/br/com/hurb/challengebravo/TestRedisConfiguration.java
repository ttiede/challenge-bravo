package br.com.hurb.challengebravo;

import br.com.hurb.challengebravo.config.RedisProperties;
import br.com.hurb.challengebravo.domain.Currency;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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
class UserRepositoryIntegrationTest {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Test
    public void shouldSaveUser_toRedis() {
        UUID id = UUID.randomUUID();
        Currency user = new Currency(id.toString(), "name", "1");

        Currency saved = currencyRepository.save(user);
        assertNotNull(saved);
        Currency search = (Currency) currencyRepository.findAll();
        assertNotNull(saved);

    }
}