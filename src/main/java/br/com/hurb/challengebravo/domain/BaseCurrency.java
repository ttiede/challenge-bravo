package br.com.hurb.challengebravo.domain;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@RedisHash("BaseCurrency")
public class BaseCurrency implements Serializable {
    @Indexed
    private
    String id;
    @Indexed
    private
    String name;
    private List<Currency> currencies;

    public BaseCurrency(String id, String name, List<Currency> currencies) {
        super();
        this.id = id;
        this.name = name;
        this.currencies = currencies;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseCurrency)) return false;
        BaseCurrency that = (BaseCurrency) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getName(), that.getName()) &&
            Objects.equals(getCurrencies(), that.getCurrencies());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCurrencies());
    }

    @Override
    public String toString() {
        return "BaseCurrency{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", currencies=" + currencies +
            '}';
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

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }
}
