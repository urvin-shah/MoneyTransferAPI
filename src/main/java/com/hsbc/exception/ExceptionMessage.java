package com.hsbc.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.Date;

public class ExceptionMessage {
    private String exception;
    private HttpStatus errorCode;
    private LocalDate date;

    public ExceptionMessage(String exception, HttpStatus errorCode, LocalDate date) {
        this.exception = exception;
        this.errorCode = errorCode;
        this.date = date;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(HttpStatus errorCode) {
        this.errorCode = errorCode;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


}
