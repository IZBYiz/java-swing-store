package com.cy.store.service.impl;

import com.cy.store.entity.Cart;
import com.cy.store.entity.Product;
import com.cy.store.entity.User;
import com.cy.store.mapper.CartMapper;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.ICartService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@SuppressWarnings({"all"})
public class CartServiceImpl implements ICartService {

    @Autowired
    public CartMapper cartMapper;

    @Autowired
    public ProductMapper productMapper;
    @Autowired
    public UserMapper userMapper;

    @Override
    public void addCart(Integer uid, Integer pid, Integer num) {
        Product byId = productMapper.findById(pid);
        if (byId == null) {
            throw new ProductNotFoundException("商品加载异常");
        }
        Cart cart = new Cart();
        cart.setUid(uid);
        cart.setPid(byId.getId());
        cart.setPrice(byId.getPrice());
        cart.setNum(num);
        User byUid = userMapper.findByUid(uid);
        if (byUid == null) {
            throw new UserNotFoundException("用户加载异常");
        }
        cart.setCreatedUser(byUid.getUsername());
        cart.setCreatedTime(new Date());
        cart.setModifiedUser(byUid.getCreatedUser());
        cart.setModifiedTime(new Date());
        Cart cart1 = cartMapper.findByPidAndUid(byUid.getUid(), byId.getId());
        if (cart1 != null) {
            cart1.setNum(cart1.getNum() + num);
            cart1.setModifiedTime(new Date());
            Integer integer = cartMapper.updateNumById(byId.getId(), cart1.getNum());
            if (integer != 1) {
                throw new UpdateException("更新数据时产生异常");
            }
        } else {
            Integer insert = cartMapper.insert(cart);
            if (insert != 1) {
                throw new InsertException("商品加入购物车产生异常");
            }
        }
    }

    @Override
    public void deleteCart(Integer uid, Integer pid, Integer num) throws DeleteCartException {
        User byUid = userMapper.findByUid(uid);
        if (byUid == null) {
            throw new UserNotFoundException("用户加载异常");
        }
        Cart byPidAndUid = cartMapper.findByPidAndUid(byUid.getUid(), pid);
        if (byPidAndUid.equals(null)) {
            throw new ProductNotFoundException("购物车加载异常");
        }
        if (byPidAndUid.getNum() - num >= 1) {
            byPidAndUid.setNum(byPidAndUid.getNum() - num);
            cartMapper.updateNumById(byPidAndUid.getPid(),
                    byPidAndUid.getNum());
        } else if (byPidAndUid.getNum() - num == 0) {
            cartMapper.deleteByPid(byPidAndUid.getPid());
        }else {
            throw new DeleteCartException("删除商品产生异常");
        }
    }
}
