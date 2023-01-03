package com.yichen.casetest.test.basetype;

import com.yichen.casetest.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Qiuxinchao
 * @date 2023/1/3 15:56
 * @describe 数字测试
 */
@Slf4j
public class NumberTest {

    public static void main(String[] args) {
        toHex();
        StringUtils.divisionLine();
        objectToStringPrint();
    }

    public static void toHex(){
        Long data = -3923293971815350686L;
        log.info("{} {}", String.format("%08x", data), String.format("%08x", -data));
        data = ~data;
        log.info("{}", String.format("%08x", data));
    }

    public static void objectToStringPrint(){
        //  %08d 宽度8位，不足0填充
        log.info("{}", String.format("%08d", 1234));
        log.info("{}", String.format("%8d", 1234));
        log.info("{}", String.format("%8d", 123456789));
    }



}
