package com.yichen.casetest.bugtest.methodOverload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Qiuxinchao
 * @date 2023/7/17 9:00
 * @describe 方法重载测试
 *
 */
@Slf4j
public class MethodOverloadTest {

    public static void main(String[] args) {
        A a = A.builder().msg("hello world").build();
        CI ci = new CI();
        B b = B.builder().msg(a.getMsg()).build();
        ci.show(a);
        ci.show(b);
        A a1 = null;
        B b1 = null;
        ci.show(a1);
        ci.show(b1);
        // 都是符合多个，不能这么使用
//        ci.show(null);
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    protected static class A {
        private String msg;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    protected static class B{
        private String msg;
    }

    protected interface C {
        void show(A a);
        void show(B b);
    }

    /**
     * 即使入参是null
     */
    protected static class CI implements C{
        @Override
        public void show(A a) {
            log.info("A");
//            log.info("A => {}", a.getMsg());
        }

        @Override
        public void show(B b) {
            log.info("B");
//            log.info("B => {}", b.getMsg());
        }
    }

}
