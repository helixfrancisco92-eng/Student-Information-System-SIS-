package com.group3.SIS.common.exception;

public class NoSuchCustomerExistsException extends RuntimeException {
    public NoSuchCustomerExistsException(String message) {
        super(message);
    }
}
