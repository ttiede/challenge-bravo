package br.com.hurb.challengebravo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeBravoApplication {

    private static final Logger LOGGER=LoggerFactory.getLogger(ChallengeBravoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ChallengeBravoApplication.class, args);
    }
}