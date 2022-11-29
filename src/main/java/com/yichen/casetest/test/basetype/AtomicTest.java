package com.yichen.casetest.test.basetype;

import com.yichen.casetest.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/11/29 16:14
 * @describe atomic 相关测试
 */
@Slf4j
public class AtomicTest {


    public static void main(String[] args) {
        atomicBooleanDefaultValueTest();
        StringUtils.divisionLine();
    }

    private static void atomicBooleanDefaultValueTest(){
        AtomicBoolean ab = new AtomicBoolean();
        log.info("defaultValue {}", ab.get());
        ab.compareAndSet(true, false);
        log.info("change value true to false {}", ab.get());
        ab.compareAndSet(true, false);
        log.info("change value false to true {}", ab.get());
    }

}
