package br.com.hurb.challengebravo.response;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class ResponseConvertedCurrency implements Serializable {
    private Map<String, CurrencyResponse> original;
    private Map<String, CurrencyResponse> result;

    public ResponseConvertedCurrency() { }

    public Map<String, CurrencyResponse> getOriginal() {
        return original;
    }

    public void setOriginal(Map<String, CurrencyResponse> original) {
        this.original = original;
    }

    public Map<String, CurrencyResponse> getResult() {
        return result;
    }

    public void setResult(Map<String, CurrencyResponse> result) {
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
