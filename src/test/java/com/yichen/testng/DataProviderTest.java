package com.yichen.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author banYu
 * @version 1.0
 * @date 2024/1/17 16:46
 * @describe dataProvider基本研究
 */
public class DataProviderTest {

    @DataProvider
    public Object[][] getTestData(){
        return new Object[][]{
                {"shanliang", 18, "唱跳rap"}
        };
    }


    @Test(dataProvider = "getTestData")
    public void test(String name, int age, String hobby){
        System.out.printf("名字:%s,年龄:%s,爱好:%s%n", name, age, hobby);
    }

}
