package com.cy.store.mapper;

import com.cy.store.entity.Address;
import com.cy.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@SuppressWarnings({"all"})
public class AddressMapperTest {
    @Autowired
    public UserMapper userMapper;

    @Autowired
    public AddressMapper addressMapper;

    @Test
    public void insert() {
        User result = userMapper.findByUid(1);
        Integer uid = result.getUid();
        Address address = new Address();
        String name = result.getUsername();
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
        address.setPhone(result.getPhone());
        address.setTel("10086");
        address.setTag("学校");
        address.setIsDefault(1);
        address.setCreatedUser(result.getUsername());
        address.setCreatedTime(new Date());
        address.setModifiedUser(result.getUsername());
        address.setModifiedTime(new Date());
        Integer row = addressMapper.insert(address);
        if(row==1){
            System.out.println("地址插入成功~~");
        }
    }

    @Test
    public void count(){
        Integer count = addressMapper.count(1);
        System.out.println(count);
    }

    @Test
    public void countByUid(){
        Address list = addressMapper.findByAid(24);
        if(list!=null){
            addressMapper.updateNonDefaultByUid(18);
            addressMapper.updateDefaultByAid(24,"LUNVH",new Date());
        }
    }
}
