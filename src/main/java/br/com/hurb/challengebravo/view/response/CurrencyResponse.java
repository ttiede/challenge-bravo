package br.com.hurb.challengebravo.view.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class CurrencyResponse  implements Serializable {
    private final String currency;
    private final BigDecimal amount;

    public CurrencyResponse(String currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }


    public String getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

}
