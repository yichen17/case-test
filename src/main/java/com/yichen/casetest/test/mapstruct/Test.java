package com.yichen.casetest.test.mapstruct;

import java.math.BigDecimal;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/8/5 16:59
 * @describe
 */
public class Test {

    public static void main(String[] args) {
        Person person = new Person();
        person.setDescribe("测试");
        person.setAge(18);
        person.setName("张三");
        person.setHeight(170.5);
        person.setSource(new BigDecimal("100"));

        PersonDTO dto = PersonMapper.INSTANCT.convert(person);

        System.out.println(dto);
    }

}
