package com.yichen.casetest.test.basetype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Qiuxinchao
 * @date 2023/1/30 17:28
 * @describe 内部类测试
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InnerClassTest {

    private String desc;

    public interface TestInterface{
        String show();
    }

    public interface TestInterface1{
        String say();
    }



    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class InnerClass implements TestInterface, TestInterface1{
        private String name;
//        private static String position;

        public String print(){
            return String.format("==>%s", desc);
        }

        @Override
        public String show() {
            return "show";
        }

        @Override
        public String say() {
            return "say";
        }
    }




}
