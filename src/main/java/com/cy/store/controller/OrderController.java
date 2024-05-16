package com.cy.store.controller;

import com.cy.store.service.IOrderService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    private IOrderService orderService;

    @RequestMapping("/cart")
    public JsonResult<Void> cart(HttpSession session, Integer aid) {
        Integer uid = getuidFromSession(session);
        orderService.cart(aid, uid);
        return new JsonResult<Void>(OK);
    }
}
