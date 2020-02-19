package br.com.hurb.challengebravo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.math.BigDecimal;

@JsonSerialize
public class CurrencyDto implements Serializable {
    @JsonProperty
    private BigDecimal BRL, EUR, BTC, ETH, USD;

    public BigDecimal getBRL() {
        return BRL;
    }

    public BigDecimal getEUR() {
        return EUR;
    }

    public BigDecimal getBTC() {
        return BTC;
    }

    public BigDecimal getETH() {
        return ETH;
    }

    public BigDecimal getUSD() {
        return USD;
    }

}
