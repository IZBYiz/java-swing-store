package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class AddressServiceTest {
    @Autowired
    public IAddressService addressService;

    @Autowired
    public IUserService userService;

    @Test
    public void addNewAddress() {
        Integer uid = 2;
//        Integer uid = result.getUid();
        Address address = new Address();
        String name = "root1";
        address.setName(name);
        address.setUid(uid);
        address.setProvinceName("湖北");
        address.setProvinceCode("1010");
        address.setCityName("武汉");
        address.setCityCode("1011");
        address.setAreaName("新洲");
        address.setAreaCode("1012");
        address.setZip("108601");
        address.setAddress("武汉生物工程学院");
        address.setPhone("10010");
        address.setTel("10086");
        address.setTag("学校");
        address.setIsDefault(1);
        address.setCreatedUser(name);
        address.setCreatedTime(new Date());
        address.setModifiedUser(name);
        address.setModifiedTime(new Date());
        addressService.addNewAddress(uid,name,address);
        System.out.println("插入成功~");
    }

    @Test
    public void setDefault(){
       addressService.setDefault(29,18,"too");
    }

    @Test
    public void deleteAddress(){
        addressService.deleteAddress(27,18,"ZS");
    }
}
