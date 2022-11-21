package com.yichen.casetest.test.fastjon;

import lombok.Data;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/9/21 16:43
 * @describe
 */
@Data
public class UserString {

    private String name;

    private Integer age;

    private String address;

//    public boolean isAdult(){
//        return age > 18;
//    }

    public String totalName(){
        return "total => " + name;
    }

}
