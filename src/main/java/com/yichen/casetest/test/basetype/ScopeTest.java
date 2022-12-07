package com.yichen.casetest.test.basetype;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Qiuxinchao
 * @date 2022/12/7 15:59
 * @describe 作用域测试
 */
@Slf4j
public class ScopeTest extends Parent{

    protected String name = "childName";

    @Override
    public String getName(){
        return "scopeTest";
    }

    /**
     * 字段作用域测试    方法内属性优先级最高
     */
    public void scopeFieldTest(){
        String name = "yichen";
        log.info("function name {}", name);
        log.info("this name {}", this.name);
        log.info("parent name {}", super.name);
    }

    /**
     * 方法作用域测试
     */
    public void scopeFunctionTest(){
        log.info("this function {}", this.getName());
        log.info("parent function {}", super.getName());
        log.info("default function {}", getName());
    }

    public static void main(String[] args) {
        ScopeTest scopeTest = new ScopeTest();
        scopeTest.scopeFieldTest();
        scopeTest.scopeFunctionTest();
    }



}

@Data
class Parent{
    protected String name = "parentName";
    public String getName(){
        return "parent";
    }
}
