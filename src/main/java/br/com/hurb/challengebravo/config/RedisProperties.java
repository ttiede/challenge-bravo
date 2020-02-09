package br.com.hurb.challengebravo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RedisProperties {
    private final int redisPort;

    public int getRedisPort() {
        return redisPort;
    }

    public String getRedisHost() {
        return redisHost;
    }

    private final String redisHost;

    public RedisProperties(
        @Value("${spring.redis.port}") int redisPort,
        @Value("${spring.redis.host}") String redisHost) {
        this.redisPort = redisPort;
        this.redisHost = redisHost;
    }
}