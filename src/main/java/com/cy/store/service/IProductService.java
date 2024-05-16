package com.cy.store.service;

import com.cy.store.entity.Product;

import java.util.List;

public interface IProductService {

    List<Product> findHotList();

    /**
     * 根据id查询商品的信息
     * 当id不存在时，返回null
     * @param id
     */
    Product findById(Integer id);
}
