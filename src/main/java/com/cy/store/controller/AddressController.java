package com.cy.store.controller;

import com.cy.store.entity.Address;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IUserService;
import com.cy.store.util.JsonResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@SuppressWarnings({"all"})
@RestController
@RequestMapping("/address")
public class AddressController extends BaseController {
    @Autowired
    public IAddressService addressService;

    @Autowired
    public IUserService userService;

    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(HttpSession session, Address address) {
        //获取当前用户的uid
        Integer uid = getuidFromSession(session);
        //获取当前用户的用户名
        String username = getUsernameFromSession(session);
        addressService.addNewAddress(uid, username, address);
        System.out.println("插入成功~~");
        return new JsonResult<Void>(OK);
    }

    /**
     * @param session
     * @param aid
     * @return
     * @PathVariable 映射 URL 绑定的占位符 通过 @PathVariable
     * 可以将 URL 中占位符参数绑定到控制器处理方法的入参中:URL 中的 {xxx}
     * 占位符可以通过
     * @PathVariable(“xxx”) 绑定到操作方法的入参中。
     */
    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(HttpSession session, @PathVariable("aid") Integer aid) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.setDefault(aid, uid, username);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("/{aid}/delete")
    public JsonResult<Void> deleteAddress(@PathVariable("aid") Integer aid,HttpSession session){
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.deleteAddress(aid,uid,username);
        return new JsonResult<Void>(OK);
    }
}
