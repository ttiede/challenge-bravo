package br.com.hurb.challengebravo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import redis.embedded.RedisServer;

@Configuration
public class ResourcesConfig {
    @Bean("dataLoaderInit")
    @DependsOn("redisServer")
    public DataLoaderInit dataLoader(){
        return new DataLoaderInit();
    }
    @Bean("redisServer")
    public RedisServer redisServer(RedisProperties redisProperties) {
        RedisServer redisServer = new RedisServer(redisProperties.getRedisPort());
        redisServer.start();
        return redisServer;
    }
}