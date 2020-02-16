package br.com.hurb.challengebravo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;

@TestConfiguration
public class TestRedisConfiguration {

    @Autowired
    ApplicationContext context;

    @Test
    public void WhenLoadContext() {
    }
}
