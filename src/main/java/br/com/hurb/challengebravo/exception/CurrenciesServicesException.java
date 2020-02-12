package br.com.hurb.challengebravo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_FOUND)

public class CurrenciesServicesException extends RuntimeException {
    public CurrenciesServicesException(String message) {
        super(message);
    }
}