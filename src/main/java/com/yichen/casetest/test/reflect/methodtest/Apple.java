package com.yichen.casetest.test.reflect.methodtest;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/11/24 21:54
 * @describe
 */
public class Apple implements Fruits{

    @Override
    public String getColor() {
        return "red";
    }

    public boolean canEat(){
        return true;
    }

}
