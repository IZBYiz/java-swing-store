package com.cy.store.service;

import com.cy.store.service.ex.DeleteCartException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CartServiceTest {
    @Autowired
    public ICartService cartService;
    @Test
    public void test(){
        cartService.addCart(12,10000003,20);
    }

    @Test
    public void test02() throws DeleteCartException {
        cartService.deleteCart(18,10000004,4);
    }
}
