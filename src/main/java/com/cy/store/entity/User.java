package com.cy.store.entity;

import lombok.Data;

import java.io.Serializable;

/*@Data 提供类的get、set、
equals、hashCode、canEqual、toString方法*/
@Data
public class User extends BaseEntity implements Serializable {
    private Integer uid;
    private String username;
    private String password;
    private String salt;
    private String phone;
    private String email;
    private String gender;
    private String avatar;
    private Integer isDelete;
    //get和set方法，equals和haseCode()方法，toString方法
}
