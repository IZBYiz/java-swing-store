package com.cy.store.mapper;

import com.cy.store.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@SuppressWarnings({"all"})
public class ProductMapperTest {
    @Autowired
    public ProductMapper productMapper;

    @Test
    public void findHotList(){
        List<Product> hotList = productMapper.findHotList();
        System.out.println(hotList);
    }

    @Test
    public void findById(){
       Product byId = productMapper.findById(10000002);
        System.out.println(byId);
    }
}
