package com.cy.store.service.impl;

import com.cy.store.entity.Product;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.service.IProductService;
import com.cy.store.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings({"all"})
public class ProductServiceImpl implements IProductService {

    @Autowired
    public ProductMapper productMapper;


    @Override
    public List<Product> findHotList() {
        List<Product> hotList = productMapper.findHotList();
        if (hotList == null) {
            throw new ProductNotFoundException("商品查询查询异常");
        }
        return hotList;
    }

    @Override
    public Product findById(Integer id) {
        Product byId = productMapper.findById(id);
        if (byId == null) {
            throw new ProductNotFoundException("商品信息查询异常~");
        }
        return byId;
    }
}
