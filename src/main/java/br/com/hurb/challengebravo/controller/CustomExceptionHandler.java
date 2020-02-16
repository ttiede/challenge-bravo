package br.com.hurb.challengebravo.controller;

import br.com.hurb.challengebravo.error.CurrenciesNotFoundException;
import br.com.hurb.challengebravo.error.CurrenciesServicesException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(CurrenciesNotFoundException.class)
    protected ResponseEntity<Object> handleCurrencyNotFound(final CurrenciesNotFoundException ex, final WebRequest request){
        final String bodyOfResponse = "Currency not found";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(CurrenciesServicesException.class)
    protected ResponseEntity<Object> handleCurrencyException(final CurrenciesServicesException ex, final WebRequest request){
        final String bodyOfResponse = "Currency services has a error";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}