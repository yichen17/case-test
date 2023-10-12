package com.yichen.casetest.test.inherit;

import lombok.extern.slf4j.Slf4j;

/**
 * @author banYu
 * @version 1.0
 * @date 2023/10/11 15:24
 * @describe 继承相关测试
 */
@Slf4j
public class InheritTest {

    public static void main(String[] args) {
        inheritTest();
    }


    /**
     * 子类重写了父类方法后，父类调用该方法是重写后的
     */
    private static void inheritTest(){
        Male male = new Male();
        System.out.println(male.print());
        Female female = new Female();
        System.out.println(female.print());
    }


    private static abstract class Person {
        public String getName(){
            return "人";
        }

        public abstract int getCode();

        public String print(){
            return "=> 描述" + getName();
        }
    }

    private static class Male extends Person{
        @Override
        public int getCode() {
            return 0;
        }

        @Override
        public String getName() {
            return "男人";
        }
    }

    private static class Female extends Person{
        @Override
        public int getCode() {
            return 1;
        }

        @Override
        public String getName() {
            return "女人";
        }
    }


}
