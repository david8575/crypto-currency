package org.david.crytocurrency.exception;

public class UpbitClientException extends RuntimeException {
    public UpbitClientException(String message) {
        super(String.format("[ERROR] %s", message));
    }
}
