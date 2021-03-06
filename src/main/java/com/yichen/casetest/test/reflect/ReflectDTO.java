package com.yichen.casetest.test.reflect;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/3/9 14:58
 * @describe 反射实体
 */
@Data
public class ReflectDTO {


    private String name;

    private Integer age;

    private Long id;

    public BigDecimal cost;

    protected Double price;


}
