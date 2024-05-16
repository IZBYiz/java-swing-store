package com.cy.store.service.ex;

public class DeleteCartException extends Exception {
    public DeleteCartException() {
        super();
    }

    public DeleteCartException(String message) {
        super(message);
    }

    public DeleteCartException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteCartException(Throwable cause) {
        super(cause);
    }

    protected DeleteCartException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
