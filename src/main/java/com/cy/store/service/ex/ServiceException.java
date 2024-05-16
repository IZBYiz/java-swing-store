package com.cy.store.service.ex;
/**
 * 业务层异常的基类
 * 采用：throws new ServiceException("业务层产生未知异常")
 */
public class ServiceException extends RuntimeException {

    //无参构造
    public ServiceException() {
        super();
    }

    //有参构造
    public ServiceException(String message) {
        super(message);
    }

    //同时抛出异常的信息和对象
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    //抛出异常的原因
    public ServiceException(Throwable cause) {
        super(cause);
    }

    //定详细信息，运行时异常原因，抑制启用或禁用，启用或禁用写的堆栈跟踪
    protected ServiceException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
