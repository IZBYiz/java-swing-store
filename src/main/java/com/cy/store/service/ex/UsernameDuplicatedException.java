package com.cy.store.service.ex;
/**
 *用户在进行注册的时候，可能会产生用户名被占用的错误，
 *抛出一个异常：UsernameDepulicatedException异常。
 *用户名被占用的异常
 */
public class UsernameDuplicatedException extends ServiceException {
    public UsernameDuplicatedException() {
        super();
    }
    public UsernameDuplicatedException(String message) {
        super(message);
    }
    public UsernameDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }
    public UsernameDuplicatedException(Throwable cause) {
        super(cause);
    }
    protected UsernameDuplicatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
