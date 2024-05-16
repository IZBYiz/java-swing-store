package com.cy.store.mapper;

import com.cy.store.entity.Address;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.Date;
import java.util.List;

public interface AddressMapper {
    /**
     * 添加用户的地址
     *
     * @param address
     * @return
     */
    Integer insert(Address address);

    /**
     * 查询总条数
     *
     * @param uid
     * @return
     */
    Integer count(Integer uid);

    /**
     * 判断地址是否存在
     *
     * @param aid
     * @return
     */
    Address findByAid(Integer aid);

    /**
     * 将某用户的所有收货地址设置为非默认地址
     * @param uid 收货地址归属的用户id
     * @return 受影响的行数
     */
    Integer updateNonDefaultByUid(Integer uid);

    /**
     * 将指定的收货地址设置为默认地址
     * @param aid 收货地址id
     * @param modifiedUser 修改执行人
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    Integer updateDefaultByAid(
            @Param("aid") Integer aid,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);

    /**
     * 删除收货地址
     * @param aid
     * @return
     */
    Integer deleteByAid(Integer aid);

    /**
     * 根据用户uid查询当前用户最后一次被修改的收货地址数据
     *
     * @param uid 归属的用户id
     * @return 该用户最后修改的收货地址，如果该用户没有收货地址数据则返回null
     */
    Address findLastModified(Integer uid);
}
