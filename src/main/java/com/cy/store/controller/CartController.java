package com.cy.store.controller;

import com.cy.store.entity.BaseEntity;
import com.cy.store.service.ICartService;
import com.cy.store.service.ex.DeleteCartException;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/carts")
public class CartController extends BaseController {

    @Autowired
    private ICartService cartService;

    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid, Integer num,
                                      HttpSession session) {
        Integer uid = getuidFromSession(session);
        cartService.addCart(uid, pid, num);
        // 返回成功
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("/delete_to_cart")
    public JsonResult<Void> deleteCart(HttpSession session,
                                       Integer pid, Integer num) throws DeleteCartException {
        Integer uid = getuidFromSession(session);
        cartService.deleteCart(uid, pid, num);
        return new JsonResult<Void>(OK);
    }
}
