package com.cy.store.service.ex;

/**
 * 用户名没有找到，抛出异常：UsernameNotFoundException。
 * 运行时异常，业务异常
 */
public class UsernameNotFoundException extends ServiceException {
    public UsernameNotFoundException() {
        super();
    }

    public UsernameNotFoundException(String message) {
        super(message);
    }

    public UsernameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameNotFoundException(Throwable cause) {
        super(cause);
    }

    protected UsernameNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
