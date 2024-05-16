package com.cy.store.service.ex;

public class templateNotMatchException extends ServiceException{
    public templateNotMatchException() {
        super();
    }

    public templateNotMatchException(String message) {
        super(message);
    }

    public templateNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public templateNotMatchException(Throwable cause) {
        super(cause);
    }

    protected templateNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
