package br.com.hurb.challengebravo.domain;

import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Currency implements Serializable {
    @Indexed
    private
    String id;
    @Indexed
    private
    String name;
    private BigDecimal value;


    public Currency(String id, String name, BigDecimal value) {
        super();
        this.id = id;
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Currency{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", value=" + value +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Currency)) return false;
        Currency currency = (Currency) o;
        return Objects.equals(getId(), currency.getId()) &&
            Objects.equals(getName(), currency.getName()) &&
            Objects.equals(getValue(), currency.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getValue());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
