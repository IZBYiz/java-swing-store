package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * 业务层的单元测试
 */
@SpringBootTest
@SuppressWarnings({"all"})
public class UserServiceTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void reg() {
        try {
            User user = new User();
            user.setUsername("root5");
            user.setPassword("123");
            userService.reg(user);
            System.out.println("OK!!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login() {
        userService.login("zjl", "1234");
    }

    @Test
    public void changePassword() {
        userService.changePassword(15, "xm",
                "123", "1234");
    }

    @Test
    public void changeInfo() {
        User user = new User();
//        user.setUid(1);
//        user.setUsername("root");
        user.setGender("1");
        user.setEmail("123@qq.com");
        user.setPhone("54321");
        userService.changeInfo(2, "root1",user);
    }

    @Test
    public void updateIsDelete(){
        userService.updateIsDelete(2);
    }

    @Test
    public void changeAvatar(){
        userService.changeAvatar(1,"root","file2");
    }
}
