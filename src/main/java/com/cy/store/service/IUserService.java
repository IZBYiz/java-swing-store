package com.cy.store.service;

import com.cy.store.entity.User;

import javax.jws.soap.SOAPBinding;

/**
 * 用户模块 业务层 的实现类 spring层
 */
public interface IUserService {
    /**
     * 用户注册方法
     *
     * @param user 用户的数据对象
     */
    void reg(User user);

    /**
     * 用户登录功能
     *
     * @param username 用户名
     * @param password 用户密码
     * @return 当前匹配的用户数据，如果没有则返回null值
     */
    User login(String username, String password);

    /**
     * 修改用户的密码
     *
     * @param uid         查询用户的ID
     * @param username    查询用户的名称
     * @param oldPassword 获取原有的密码
     * @param newPassword 更新密码
     */
    void changePassword(Integer uid, String username,
                        String oldPassword, String newPassword);



    /**
     * 修改用户资料
     * @param uid 当前登录的用户的id
     * @param username 当前登录的用户名
     */
    void changeInfo(Integer uid, String username,User user);

    /**
     * 注销该账号
     * @param uid
     */
    void updateIsDelete(Integer uid);

    /**
     * 修改用户头像
     * @param uid 当前登录的用户的id
     * @param username 当前登录的用户名
     * @param avatar 用户的新头像的路径
     */
    void changeAvatar(Integer uid,String username,String avatar);
}
