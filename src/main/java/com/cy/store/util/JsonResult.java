package com.cy.store.util;

import lombok.Data;

import java.io.Serializable;

/**
 * Json格式的数据进行响应
 */
@Data
public class JsonResult<E> implements Serializable {
    private Integer state; //状态码
    private String message; //描述信息
    private E data; //数据

    public JsonResult() {}

    public JsonResult(Integer state) {
        this.state = state;
    }

    //异常的捕获
    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }

    public JsonResult(Integer state, E data) {
        this.data = data;
        this.state = state;
    }
}
