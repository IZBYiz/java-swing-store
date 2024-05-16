package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.entity.District;
import com.cy.store.entity.User;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.mapper.DistrictMapper;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;

@Service
@SuppressWarnings({"all"})
public class AddressServiceImpl implements IAddressService {

    @Autowired
    public UserMapper userMapper;

    @Autowired
    public AddressMapper addressMapper;

    //根据区号添加编号
    @Autowired
    public DistrictMapper districtMapper;

    @Value("${user.address.max-count}")
    private Integer MAX_COUNT;

    @Override
    public void addNewAddress(Integer uid, String username,
                              Address address) {
        User result = userMapper.findByUid(uid);
        if (result.getUsername() == null || !result.getUsername().equals(username) ||
                result.getIsDelete().equals(1)) {
            throw new UsernameNotFoundException("用户不存在~,或用户已经注销~");
        }
        Integer count = addressMapper.count(uid);
        // 判断数量是否达到上限值
        if (count >= MAX_COUNT) {
            // 是：抛出AddressCountLimitException
            throw new AddressCountLimitException("收货地址数量已经达到上限(" + MAX_COUNT + ")！");
        }
        address.setUid(result.getUid());

        address.setName(result.getUsername());
        //省名称和省编码
        address.setProvinceName(address.getProvinceName());
        District byParent = districtMapper.findByParent(address.getProvinceName());
        System.out.println(byParent);
        address.setProvinceCode(byParent.getParent());
        //市名称和市编码
        address.setCityName(address.getCityName());
        District byCity = districtMapper.findByCity(address.getCityName());
        address.setCityCode(byCity.getCode());
        //区县名称和区县编码
        address.setAreaName(address.getAreaName());
        District byArea = districtMapper.findByArea(address.getAreaName());
        address.setAreaCode(byArea.getCode());


        if (byParent == null || byArea == null || byCity == null) {
            throw new DistrictErrorException("地址输入错误,请重新输入~");
        }
        address.setZip(address.getZip());

        address.setAddress(address.getAddress());

        address.setPhone(result.getPhone());

        address.setTel(address.getTel());

        address.setTag(address.getTag());

        //设置当前地址为非默认地址
        address.setIsDefault(0);


        //用户的基本信息
        address.setCreatedUser(result.getUsername());
        address.setCreatedTime(new Date());
        address.setModifiedUser(result.getUsername());
        address.setModifiedTime(new Date());

        addressMapper.insert(address);
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String useranme) {
        Address list = addressMapper.findByAid(aid);
        if (list != null) {
            if (list.getUid().equals(uid)) {
                addressMapper.updateNonDefaultByUid(uid);
                Integer row = addressMapper.updateDefaultByAid(aid, useranme, new Date());
                if (row == null) {
                    throw new UsernameNotFoundException("用户存在异常~");
                }
            } else {
                throw new UsernameNotFoundException("用户存在异常");
            }
        } else {
            throw new AddressNotExistException("用户地址不存在");
        }
    }

    @Override
    public void deleteAddress(Integer aid, Integer uid, String username) {
        // 根据参数aid，调用findByAid()查询收货地址数据
        Address result = addressMapper.findByAid(aid);
        if (result == null) {
            throw new DeleteException("删除地址产生未知错误");
        }
        if (!uid.equals(result.getUid())) {
            throw new UsernameNotFoundException("该用户无法进行删除");
        }
        Integer row = addressMapper.deleteByAid(aid);
        if (row != 1) {
            throw new DeleteException("删除地址失败");
        }
        if (result.getIsDefault() == 0) {
            return;
        } else {
            // 调用持久层的countByUid()统计目前还有多少收货地址
            Integer count = addressMapper.count(uid);
            if (count == 0) {
                return;
            } else {
                Address lastModified = addressMapper.findLastModified(uid);
                Integer row2 = addressMapper.updateDefaultByAid(lastModified.getAid(), username, new Date());
                if (row2 != 1) {
                    // 是：抛出UpdateException
                    throw new UpdateException("更新收货地址数据时出现未知错误，请联系系统管理员");
                }
            }
        }
    }

}
