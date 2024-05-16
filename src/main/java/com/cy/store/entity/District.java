package com.cy.store.entity;

import lombok.Data;

/**
 * 省/市/区数据的实体类
 * 根据区名去调用省市区的编号
 */
@Data
public class District extends BaseEntity {
    private Integer id;
    private String parent;
    private String code;
    private String name;
}
