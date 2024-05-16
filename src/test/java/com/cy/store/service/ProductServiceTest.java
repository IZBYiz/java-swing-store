package com.cy.store.service;

import com.cy.store.entity.Product;
import com.cy.store.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
@SpringBootTest
public class ProductServiceTest {
    @Autowired
    public IProductService productService;

    @Test
    public void findHotList() {
        List<Product> hotList = productService.findHotList();
        System.out.println(hotList);
    }

    @Test
    public void findById(){
        Product byId = productService.findById(12);
        System.out.println(byId);
    }
}
