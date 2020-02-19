package br.com.hurb.challengebravo.config;


import org.apache.coyote.http2.Http2Protocol;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import redis.embedded.RedisServer;

@Configuration
public class ResourcesConfig {
    RedisServer redisServer;

    @Bean("dataLoaderInit")
    public DataLoaderInit dataLoader() {
        return new DataLoaderInit();
    }

    @Bean
    public ConfigurableServletWebServerFactory tomcatCustomizer() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers(connector -> connector.addUpgradeProtocol(new Http2Protocol()));
        return factory;
    }
}
