package br.com.hurb.challengebravo.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class DataLoader implements ApplicationRunner {

    public void run(ApplicationArguments args) {
        System.out.println("run");
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
            = "https://min-api.cryptocompare.com/data/";
        ResponseEntity<MyObject> response
            = restTemplate.getForEntity(fooResourceUrl + "/price?fsym=USD&tsyms=BRL,EUR,BTC,ETH,USD", MyObject.class);
        System.out.println(response);

    }
}

class MyObject  {
    @JsonProperty(value="response")
    private Map<String,String> response;
}