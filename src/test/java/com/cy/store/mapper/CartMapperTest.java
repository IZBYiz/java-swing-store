package com.cy.store.mapper;

import com.cy.store.entity.Cart;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class CartMapperTest {
    @Autowired
    public CartMapper cartMapper;

    @Test
    public void insert() {
        Cart cart = new Cart();
        cart.setUid(1);
        cart.setPid(1244);
        cart.setPrice(978L);
        cart.setNum(1);
        cart.setCreatedUser("李华");
        cart.setCreatedTime(new Date());
        cart.setModifiedUser("李华");
        cart.setModifiedTime(new Date());
        Cart cart1 = cartMapper.findByPidAndUid(1, 1244);
        if (cart1 != null) {
            cart1.setNum(cart1.getNum() + 1);
            cart1.setModifiedTime(new Date());
            cartMapper.updateNumById(1244, cart1.getNum());
        } else {
            cartMapper.insert(cart);
        }
    }

    @Test
    public void deleteByPid() {
        cartMapper.deleteByPid(1243);
    }

    @Test
    public void findByUid() {
        List<Cart> byUid = cartMapper.findByUid(12);
        int sum = 0;
        for (Cart c : byUid
        ) {
            System.out.println(c);
            Integer cid = c.getCid();
            System.out.println(cid);
            sum+=c.getNum()*c.getPrice();
        }
        System.out.println(sum);
    }
}
