package br.com.hurb.challengebravo.view.response;

import java.io.Serializable;
import java.util.Objects;

public class ExchangeCurrencyResponse implements Serializable {
    private CurrencyResponse original;
    private CurrencyResponse result;

    public ExchangeCurrencyResponse() { }

    public CurrencyResponse getOriginal() {
        return original;
    }

    public void setOriginal(CurrencyResponse original) {
        this.original = original;
    }

    public CurrencyResponse getResult() {
        return result;
    }

    public void setResult(CurrencyResponse result) {
        this.result = result;
    }
}
