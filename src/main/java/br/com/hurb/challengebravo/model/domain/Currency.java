package br.com.hurb.challengebravo.model.domain;

import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.math.BigDecimal;

public class Currency implements Serializable {
    @Indexed
    private final
    String id;
    private BigDecimal value;


    public Currency(String id, BigDecimal value) {
        super();
        this.id = id;
        this.value = value;

    }

    public String getId() {
        return id;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}
