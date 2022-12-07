package com.yichen.casetest.test.reflect.methodtest;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/11/24 21:56
 * @describe
 */
public class ArtificialApple extends Apple{

    public String meterial(){
        return "iron";
    }

    @Override
    public boolean canEat() {
        return false;
    }

}
