package br.com.hurb.challengebravo.response;

import java.io.Serializable;
import java.util.Objects;

public class ResponseConvertedCurrency implements Serializable {
    private CurrencyResponse original;
    private CurrencyResponse result;

    public ResponseConvertedCurrency() { }

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

    @Override
    public String toString() {
        return "ResponseConvertedCurrency{" +
            "original=" + original +
            ", result=" + result +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResponseConvertedCurrency)) return false;
        ResponseConvertedCurrency that = (ResponseConvertedCurrency) o;
        return Objects.equals(getOriginal(), that.getOriginal()) &&
            Objects.equals(getResult(), that.getResult());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOriginal(), getResult());
    }
}
