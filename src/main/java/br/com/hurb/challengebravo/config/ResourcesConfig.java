package br.com.hurb.challengebravo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import redis.embedded.RedisServer;

@Configuration
public class ResourcesConfig {
    RedisServer redisServer;

    @Bean("dataLoaderInit")
    @DependsOn("redisServerStart")
    public DataLoaderInit dataLoader(){
        return new DataLoaderInit();
    }

    @Bean("redisServer")
    public RedisServer redisServer(RedisProperties redisProperties) {
        redisServer = new RedisServer(redisProperties.getRedisPort());
        return redisServer;
    }

    @DependsOn("redisServer")
    @Bean("redisServerStart")
    public void postConstruct() {
        if(!redisServer.isActive())
            redisServer.start();
    }
}