package com.yichen.casetest.test.mapstruct;

import com.yichen.casetest.utils.FastJsonUtils;

import java.math.BigDecimal;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/8/5 16:59
 * @describe
 */
public class Test {

    public static void main(String[] args) {
        test1();
//        StringUtils.divisionLine();
    }
    private static void test1(){
        Person person = new Person();
        person.setDescribe("测试");
        person.setAge(18);
        person.setName("张三");
        person.setHeight(170.5);
        person.setSource(new BigDecimal("100"));
        person.setSex(SexEnum.BOY);
        person.setAddress(Address.builder().name("shanghai").latitude("120").longitude("74").build());
        person.setRainDay(false);

        PersonDTO dto = PersonMapper.INSTANCT.convert(person);

        System.out.println(FastJsonUtils.toJson(dto));
    }

}
