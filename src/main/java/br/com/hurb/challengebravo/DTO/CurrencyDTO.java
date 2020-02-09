package br.com.hurb.challengebravo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.math.BigDecimal;

@JsonSerialize
public class CurrencyDTO implements Serializable {
    @JsonProperty
    private BigDecimal BRL, EUR, BTC, ETH, USD;

    public BigDecimal getBRL() {
        return BRL;
    }

    public void setBRL(BigDecimal BRL) {
        this.BRL = BRL;
    }
    public BigDecimal getEUR() {
        return EUR;
    }

    public void setEUR(BigDecimal EUR) {
        this.EUR = EUR;
    }
    public BigDecimal getBTC() {
        return BTC;
    }

    public void setBTC(BigDecimal BTC) {
        this.BTC = BTC;
    }

    public BigDecimal getETH() {
        return ETH;
    }

    public void setETH(BigDecimal ETH) {
        this.ETH = ETH;
    }

    public BigDecimal getUSD() {
        return USD;
    }

    public void setUSD(BigDecimal USD) {
        this.USD = USD;
    }
}
