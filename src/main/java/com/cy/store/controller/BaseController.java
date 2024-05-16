package com.cy.store.controller;

import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.ProductNotFoundException;
import com.cy.store.service.ex.UsernameDuplicatedException;
import com.cy.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;
import java.rmi.ServerException;

//控制层的基类，做异常处理
@SuppressWarnings({"all"})
public class BaseController {
    public static final int OK = 200;

    //请求处理方法，这个方法的返回值就是需要传递给前端的数据
    //自动将异常对象传递给此方法的参数列表上
    //项目中产生了异常，被统一拦截到此方法中，这个方法此时就是充当的是请求处理方法，
    // 方法的返回值直接给到前端
    @ExceptionHandler(ServerException.class) //用于统一处理抛出的异常
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicatedException) {
            result.setState(4000);
            result.setMessage("用户名被占用");
        } else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("用户在注册时产生未知的异常");
        } else if (e instanceof ProductNotFoundException) {
            result.setState(4006);
            result.setMessage("商品信息查询异常");
        }
        return result;
    }


    /**
     * 获取session对象中的uid
     *
     * @param session session对象
     * @return 当前登陆的用户uid的值
     */
    protected final Integer getuidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 获取当前登陆用户的username
     *
     * @param session session对象
     * @return 当前登陆用户的用户名
     */
    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }
}
