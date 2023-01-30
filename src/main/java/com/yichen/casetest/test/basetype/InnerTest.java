package com.yichen.casetest.test.basetype;

import com.yichen.casetest.utils.FastJsonUtils;
import com.yichen.casetest.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Qiuxinchao
 * @date 2023/1/30 17:30
 * @describe 内部测试
 */
@Slf4j
public class InnerTest {

    public static void main(String[] args) {
        innerClassTest();
        StringUtils.divisionLine();
        innerStaticClassTest();
        StringUtils.divisionLine();
        innerClassTest2();
        StringUtils.divisionLine();
        commonCase();
    }

    /**
     * can't create non-static inner class instance
     */
    private static void innerClassTest(){
        try {
            String data = "{\"name\":\"伴语\"}";
            InnerClassTest.InnerClass innerClass = FastJsonUtils.fromJson(data, InnerClassTest.InnerClass.class);
            log.info("{}", innerClass.getName());
        }
        catch (Exception e){
            log.error("{}", e.getMessage(), e);
        }

    }

    private static void innerStaticClassTest(){
        try {
            String data = "{\"name\":\"伴语\"}";
            InnerStaticClassTest.InnerClass innerClass = FastJsonUtils.fromJson(data, InnerStaticClassTest.InnerClass.class);
            log.info("{}", innerClass.getName());
        }
        catch (Exception e){
            log.error("{}", e.getMessage(), e);
        }
    }

    private static void innerClassTest2(){
        try {
            InnerClassTest test = new InnerClassTest("shanliang");
            String data = "{\"name\":\"伴语\"}";
            InnerClassTest.InnerClass innerClass = FastJsonUtils.fromJson(data, InnerClassTest.InnerClass.class);
            log.info("{}", innerClass.getName());
        }
        catch (Exception e){
            log.error("{}", e.getMessage(), e);
        }

    }

    private static void commonCase(){
        InnerStaticClassTest innerStaticClassTest = new InnerStaticClassTest("InnerStaticClassTest");
        InnerClassTest innerClassTest = new InnerClassTest("InnerClassTest");
        InnerStaticClassTest.InnerClass innerClass_1 = new InnerStaticClassTest.InnerClass("_1");
        InnerClassTest.InnerClass innerClass_2 = innerClassTest.new InnerClass("_2");
        log.info("inner static {} {}", innerStaticClassTest, innerClass_1);
        log.info("inner {} {}", innerClassTest, innerClass_2);
    }

}
