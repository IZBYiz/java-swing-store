package com.cy.store.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private IOrderService orderService;

    @Test
    public void test() {
        orderService.cart(22, 18);
    }
}
