package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@Service
@SuppressWarnings({"all"})
public class UserServiceImpl implements IUserService {
    //调用持久层的对象
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户进行信息注册
     *
     * @param user 用户的数据对象
     */
    @Override
    public void reg(User user) {
        //通过user参数来获取传递过来的username
        String username = user.getUsername();
        //调用findByUsername(username)判断用户是否被注册过
        User byUsername = userMapper.findByUsername(username);
        if (byUsername != null) {
            throw new UsernameDuplicatedException("用户名已经别注册~请重新输入用户名！");
        } else {
            user.setUsername(username);
        }
        //密码加密处理的实现:md5算法的形式
        //(串+password+串)-----md5算法进行加密,连续加载三次
        //颜值+password+颜值----颜值就是一个随机的字符串
        String oldePassword = user.getPassword();
        /**获取一个随机颜
         * toString():返回表示 Integer 值的 String 对象
         * toUpperCase: 方法将字符串小写字符转换为大写
         * */
        String salt = UUID.randomUUID().toString().toUpperCase(Locale.ROOT);
        //补全颜值
        user.setSalt(salt);
        //将密码和颜值作为一个整体加密处理
        String md5Password = getMD5Password(oldePassword, salt);
        //将加密的密码重新补全到user对象中
        user.setPassword(md5Password);
        //补全数据:is_delete设置为0
        user.setIsDelete(0);
        //创建者和创建时间，修改者和修改时间是同一个人
        user.setCreatedUser(username);
        user.setCreatedTime(new Date());
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        //执行注册业务功能的实现(rows==1),插入成功
        Integer rows = userMapper.insert(user);
        if (rows != 1) {
            throw new InsertException("用户在注册过程中产生了未知的异常");
        }
    }

    /**
     * 用户使用用户名和用户信息进行登陆
     *
     * @param username 用户名
     * @param password 用户密码
     * @return
     */
    @Override
    public User login(String username, String password) {
        //从数据库中获取表单中输入的用户名
        User result = userMapper.findByUsername(username);
        String name = result.getUsername();
        if (result == null) {
            throw new UsernameNotFoundException("用户数据不存在，请进行注册信息");
        }

        result.getIsDelete();
        //从数据库中获取颜值
        String salt = result.getSalt();
        //对输入的密码进行颜值加密
        String newPassword = getMD5Password(password, salt);
        //把数据库中的密码和颜值加密的密码进行比较
        String oldPassword = result.getPassword();
        if (!oldPassword.equals(newPassword)) {
            throw new PasswordNotMatchException("密码输入错误，请重新输入密码");
        }
        //判断is_delete字段的值是否为1表示字段标记为删除
        Integer isDelete = result.getIsDelete();
        if (isDelete == 1) {
            throw new UsernameNotFoundException("用户数据不存在(用户已经被删除)");
        }
        //调用mapper层的findByUsername来查询用户的数据
        User user = userMapper.findByUsername(username);
        //将用户的数据返回,返回的数据是为了辅助其他页面做数据展示使用(uid,username,avatar)
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User byUid = userMapper.findByUid(uid);
        if (!username.equals(byUid.getUsername())) {
            throw new UpdateException("用户名错误");
        }
        if (byUid.getIsDelete().equals("1")) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }
        //获取颜值
        String salt = byUid.getSalt();
        //对旧密码进行加密
        String md5Password = getMD5Password(oldPassword, salt);
        if (!md5Password.equals(byUid.getPassword())) {
            throw new UpdateException("密码输入错误~");
        }
      /*  if (byUid.getIsDelete().equals(1) || byUid == null) {
            throw new UpdateException("用户也注销,请先进行注册");
        }*/
        String md5Password1 = getMD5Password(newPassword, salt);
        if (md5Password.equals(md5Password1)) {
            throw new UpdateException("新密码不能与旧密码一样,请重新输入!!");
        }
        System.out.println("密码修改成功~");
        byUid.setSalt(salt);
        userMapper.updataPasswordByUid(byUid.getUid(), md5Password1, byUid.getModifiedUser(), new Date());
    }


    /**
     * 修改用户的信息
     *
     * @param uid 当前登录的用户的id
     * @return
     */
    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if (result.getIsDelete().equals("1")) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }
        String phone = user.getPhone();
        if (phone.length() != 5) {
            throw new templateNotMatchException("号码格式不对");
        }
        user.getIsDelete();
        result.setPhone(phone);
        String email = user.getEmail();
        if (!email.substring(email.length() - 7).equals("@qq.com") || email.length() <= 9) {
            throw new templateNotMatchException("不符合邮件格式模式");
        }
        result.setEmail(email);
        String gender = user.getGender();
        if (gender.equals("1") || gender.equals("0")) {
            result.setGender(gender);
        } else {
            throw new templateNotMatchException("输入的内容不对，请重新输入");
        }
        result.setModifiedUser(username);
        result.setModifiedTime(new Date());
        Integer row = userMapper.updateInfoByUid(result);
        System.out.println("信息修改成功");

    }

    @Override
    public void updateIsDelete(Integer uid) {
        User byUid = userMapper.findByUid(uid);
        if (byUid.getUsername() == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        userMapper.updateIsDelete(byUid.getUid());
    }

    /**
     * @param uid      当前登录的用户的id
     * @param username 当前登录的用户名
     * @param avatar   用户的新头像的路径
     */
    @Override
    public void changeAvatar(Integer uid, String username, String avatar) {
        User result = userMapper.findByUid(uid);
        if (!result.getUsername().equals(username) || result == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        Integer isDelete = result.getIsDelete();
        if (isDelete.equals(1)) {
            throw new UsernameNotFoundException("该用户已经被注销");
        }
        userMapper.updateAvatarByUid(result.getUid(), avatar,
                result.getModifiedUser(),
                new Date());
    }


    //定义一个md5的算法加密
    private String getMD5Password(String password, String salt) {
        //md5加密算法方法的调用
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password +
                    salt).getBytes(StandardCharsets.UTF_8)).
                    toUpperCase(Locale.ROOT);
        }
        return password;
    }
}
