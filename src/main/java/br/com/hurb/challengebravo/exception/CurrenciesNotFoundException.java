package br.com.hurb.challengebravo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class CurrenciesNotFoundException extends RuntimeException {
    public CurrenciesNotFoundException(String message) {
        super(message);
    }
}