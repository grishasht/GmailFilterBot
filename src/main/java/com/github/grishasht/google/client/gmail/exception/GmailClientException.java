package com.github.grishasht.google.client.gmail.exception;

public class GmailClientException extends RuntimeException {

    public GmailClientException() {
    }

    public GmailClientException(String message) {
        super(message);
    }

    public GmailClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public GmailClientException(Throwable cause) {
        super(cause);
    }
}
