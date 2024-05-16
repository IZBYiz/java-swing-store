package com.cy.store.mapper;

import com.cy.store.entity.District;

public interface DistrictMapper {
    /**
     * 根据区的名称去调用省编号
     *
     * @param name
     * @return
     */
    District findByParent(String name);

    /**
     * 根据区的名称去调市的编号
     *
     * @param name
     * @return
     */
    District findByCity(String name);

    /**
     * 根据区的名称去调区的编号
     *
     * @param name
     * @return
     */
    District findByArea(String name);

}
