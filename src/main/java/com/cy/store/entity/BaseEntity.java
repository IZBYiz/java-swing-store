package com.cy.store.entity;

import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;
import java.io.Serializable;
import java.util.Date;
//公共字段,实体类的基类
@Data
public class BaseEntity implements Serializable {
    private String createdUser;
    private Date createdTime;
    private String modifiedUser;
    private Date modifiedTime;
}
