package br.com.hurb.challengebravo.model.domain;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.List;

@RedisHash("BaseCurrency")
public class BaseCurrency implements Serializable {
    @Indexed
    private final
    String id;

    private final List<Currency> currencies;

    public BaseCurrency(String id, List<Currency> currencies) {
        super();
        this.id = id;
        this.currencies = currencies;
    }

    public String getId() {
        return id;
    }


    public List<Currency> getCurrencies() {
        return currencies;
    }
}
