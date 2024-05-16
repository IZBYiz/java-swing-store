package com.cy.store.service.ex;
/**
 * 正在执行插入的时候，服务器、数据库宕机。
 * 处理正在执行插入过程中所产生的异常InsertException异常
 * 数据在插入过程中所产生的异常*/
public class InsertException extends ServiceException {
    public InsertException() {
        super();
    }

    public InsertException(String message) {
        super(message);
    }

    public InsertException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsertException(Throwable cause) {
        super(cause);
    }

    protected InsertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
