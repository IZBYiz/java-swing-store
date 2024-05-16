package com.cy.store.service;

import com.cy.store.entity.Order;

public interface IOrderService {
    Order cart(Integer aid, Integer uid);
}
