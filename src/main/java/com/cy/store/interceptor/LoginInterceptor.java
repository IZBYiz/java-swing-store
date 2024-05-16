package com.cy.store.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**定义一个拦截器*/
@SuppressWarnings({"all"})
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * Controller方法处理之前
     * 检测全局session对象中是否有uid数据，如果有则放行，如果没有则重定向到登陆页面
     *
     * @param request  请求对象
     * @param response 响应对象
     * @param handler  处理器(url+Controller:映射)
     * @return 如果返回值值true表示放行当前的请求，如果返回值为false则表示拦截当前的请求。
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // HttpServletRequest 对象来获取session对象
        Object obj = request.getSession().getAttribute("uid");
        if (obj == null) {  //说明用户没有登陆过系统，则重定向到login.html页面
            response.sendRedirect("/web/login.html");
            return false;  //结束后续的调用
        }
        return true;  //请求放行
    }

    //Controller方法处理之后
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    //Controller方法处理完之后，DispatcherServlet进行视图的渲染之前，也就是说在这个方法中你可以对ModelAndView进行操作
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
