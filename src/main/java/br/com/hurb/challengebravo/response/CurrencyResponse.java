package br.com.hurb.challengebravo.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class CurrencyResponse  implements Serializable {
    private String currency;
    private BigDecimal amount;

    public CurrencyResponse(String currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CurrencyResponse{" +
            "currency='" + currency + '\'' +
            ", amount=" + amount +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrencyResponse)) return false;
        CurrencyResponse that = (CurrencyResponse) o;
        return Objects.equals(getCurrency(), that.getCurrency()) &&
            Objects.equals(getAmount(), that.getAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCurrency(), getAmount());
    }
}
