package com.cy.store.service.ex;

public class DistrictErrorException  extends ServiceException{
    public DistrictErrorException() {
        super();
    }

    public DistrictErrorException(String message) {
        super(message);
    }

    public DistrictErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public DistrictErrorException(Throwable cause) {
        super(cause);
    }

    protected DistrictErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
