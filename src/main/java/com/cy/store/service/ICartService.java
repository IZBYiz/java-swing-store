package com.cy.store.service;

import com.cy.store.service.ex.DeleteCartException;

public interface ICartService {
    /**
     * 添加商品到购物车
     *
     * @param uid
     * @param pid
     * @param num
     */
    void addCart(Integer uid, Integer pid, Integer num);

    /**
     * 把商品从购物车中删除
     *
     * @param uid
     * @param pid
     * @param num
     */
    void deleteCart(Integer uid, Integer pid, Integer num) throws DeleteCartException;
}
