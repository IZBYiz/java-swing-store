package com.cy.store.mapper;

import com.cy.store.entity.Cart;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartMapper {
    Integer insert(Cart cart);

    Integer updateNumById(Integer pid, Integer num);

    Cart findByPidAndUid(Integer uid, Integer pid);

    Integer deleteByPid(Integer pid);

    List<Cart> findByUid(Integer uid);
}
