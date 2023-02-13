package com.yichen.casetest.test.mapstruct;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/8/5 16:57
 * @describe
 */
@Data
public class Person {

    String describe;

    private String id;

    private String name;

    private int age;

    private BigDecimal source;

    private double height;

    private Date createTime;

    private SexEnum sex;

    private Address address;

}


