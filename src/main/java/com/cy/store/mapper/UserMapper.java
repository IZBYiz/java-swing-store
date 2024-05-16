package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * 用户模块的持久层接口  mysql
 */
//@Mapper
//为什么不使用注解？因为在Application中添加了MapperScan扫描
public interface UserMapper {
    /**
     * 插入用户的数据
     *
     * @param user 用户的数据
     * @return 受影响的行数（增，删，改）
     */
    Integer insert(User user);

    /**
     * 根据用户名查询用户的数据,如果找到就返回这个用户的数据，
     * 如果没有找到就返回NULL值
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 对用户进行登陆
     *
     * @param username
     * @param password
     * @return
     */
    User findByLogin(String username, String password);

    /**
     * 根据用户id修改用户的密码
     *
     * @param uid
     * @param password
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updataPasswordByUid(Integer uid, String password,
                                String modifiedUser, Date modifiedTime);

    /**
     * 根据用户的id查询用户的数据
     *
     * @param uid 用户的id
     * @return 如果找到则返回对象，反之返回null
     */
    User findByUid(Integer uid);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    Integer updateInfoByUid(User user);

    /**
     * 用户注销账号
     *
     * @param uid
     * @return
     */
    Integer updateIsDelete(Integer uid);

    /**
     * @param uid          用户的id
     * @param avatar       新头像的路径
     * @param modifiedUser 修改执行人
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     * @Param("SQL映射文件中#{}占位符的变量名"):解决的问题 ，
     * 当SQl语句的占位符和映射的参数方法参数名不一致是，
     * 需要将某个参数强行注入到某个占位符变量时，
     * 可以使用@Param这个注解来标注映射的关系
     * 根据uid更新用户的头像
     */
    Integer updateAvatarByUid(
            @Param("uid") Integer uid, @Param("avatar") String avatar,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);
}
