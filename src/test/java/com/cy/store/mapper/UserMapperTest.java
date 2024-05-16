package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * 持久层的测试类
 */
//@SpringBootTest:表示当前的类是一个测试类，不会随同项目一块打包
@SpringBootTest
//@RunWith启动这个单元测试类(单元测试是不能运行的)，
//需要传递一个参数，必须是SpringRunner的实体类型
@RunWith(SpringRunner.class)
@SuppressWarnings({"all"})
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("root");
        user.setPassword("123");
        Integer insert = userMapper.insert(user);
        System.out.println(insert);
    }

    @Test
    public void findByLogin() {
        User byUsername = userMapper.findByLogin("zby", "692F21DB47D6AFDCDEE520F113D053ED");
        System.out.println(byUsername);
    }

    @Test
    public void updataPasswordByUid() {
        User user = new User();
        user.setPassword("1234");
        user.setModifiedUser("root");
        user.setModifiedTime(new Date());
        userMapper.updataPasswordByUid(1, "1234", "root", new Date());
        System.out.println(userMapper.findByUid(1));
    }

    @Test
    public void updateInfoByUid() {
        User user = userMapper.findByUid(1);
        user.setPhone("10086");
        user.setGender("1");
        user.setEmail("321@qq.com");
        user.setModifiedUser(user.getUsername());
        user.setModifiedTime(new Date());
        userMapper.updateInfoByUid(user);
    }

    @Test
    public void updateIsDelete() {
        User byUid = userMapper.findByUid(2);
        userMapper.updateIsDelete(byUid.getUid());
    }

    @Test
    public void updateAvatarByUid() {
        User r = userMapper.findByUid(1);
        r.setAvatar("file");
//        r.setModifiedUser(r.getUsername());
//        r.setModifiedTime(new Date());
        Integer integer = userMapper.updateAvatarByUid(r.getUid(),
                r.getAvatar(), r.getUsername(), new Date());
        if (integer == 1) {
            System.out.println("头像修改成功~");
        }
    }
}
