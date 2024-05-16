package com.cy.store.entity;

import lombok.Data;

import java.io.Serializable;
/** 商品数据的实体类 */
@Data
public class Product  extends BaseEntity implements Serializable {
    private Integer id;
    private Integer categoryId; //分类id
    private String itemType; //商品系列
    private String title; //商品标签
    private String sellPoint; //商品卖点
    private Long price; //商品单价
    private Integer num; //库存数量
    private String image; //图片路径
    private Integer status; //商品状态1上架2下架3删除
    private Integer priority;//显示优先级
}
