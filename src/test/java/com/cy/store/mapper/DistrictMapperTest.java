package com.cy.store.mapper;

import com.cy.store.entity.District;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@SuppressWarnings({"all"})
public class DistrictMapperTest {
    @Autowired
    public DistrictMapper districtMapper;

    @Test
    public void findByParent() {
       District row = districtMapper.findByParent("四川省");
       District row1 = districtMapper.findByCity("泸州市");
       District row2 = districtMapper.findByArea("泸 县");
        System.out.println("查询成功:" + row);
        System.out.println("查询成功:" + row1);
        System.out.println("查询成功:" + row2);
    }


}
