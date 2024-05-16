package com.cy.store.service.impl;

import com.cy.store.entity.*;
import com.cy.store.mapper.*;
import com.cy.store.service.IAddressService;
import com.cy.store.service.ICartService;
import com.cy.store.service.IOrderService;
import com.cy.store.service.ex.UserNotFoundException;
import com.cy.store.service.ex.UsernameDuplicatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@SuppressWarnings({"all"})
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public Order cart(Integer aid, Integer uid) {
        Address byAid = addressMapper.findByAid(aid);
        Integer uid1 = byAid.getUid();
        if (!uid.equals(uid1)) {
            throw new UserNotFoundException("查询用户查询异常");
        }
        Order order = new Order();
        order.setUid(uid1);
        order.setRecvName(byAid.getName());
        order.setRecvPhone(byAid.getPhone());
        order.setRecvProvince(byAid.getProvinceName());
        order.setRecvCity(byAid.getCityName());
        order.setRecvArea(byAid.getAreaName());
        order.setRecvAddress(byAid.getAddress());
        List<Cart> byUid1 = cartMapper.findByUid(uid);
        int sum = 0;
        OrderItem orderItem = new OrderItem();
        for (Cart c : byUid1
        ) {
            sum += c.getNum() * c.getPrice();
        }
        order.setTotalPrice((long) sum);
        order.setStatus(1);
        order.setOrderTime(new Date());
        order.setPayTime(new Date());
        order.setCreatedUser(byAid.getName());
        order.setCreatedTime(new Date());
        order.setModifiedUser(byAid.getName());
        order.setModifiedTime(new Date());
        Integer row = orderMapper.insertOrder(order);
        for (Cart c : byUid1
        ) {
            Product byId = productMapper.findById(c.getPid());
            orderItem.setPid(c.getPid());
            orderItem.setOid(order.getOid());
            orderItem.setTitle(byId.getTitle());
            orderItem.setImage(byId.getImage());
            orderItem.setPrice(c.getNum() * c.getPrice());
            orderItem.setNum(c.getNum());
            orderItem.setCreatedUser(byAid.getCreatedUser());
            orderItem.setCreatedTime(byAid.getCreatedTime());
            orderItem.setModifiedUser(byAid.getModifiedUser());
            orderItem.setModifiedTime(byAid.getModifiedTime());
            orderMapper.insertOrderItem(orderItem);
        }
        if (row != null) {
            for (Cart c : byUid1
            ) {
                cartMapper.deleteByPid(c.getPid());
            }
        }
        return order;
    }
}
