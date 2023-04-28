package com.yichen.casetest.test.basetype;

import com.yichen.casetest.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Qiuxinchao
 * @date 2023/4/24 17:31
 * @describe 计算公式
 */
@Slf4j
public class CalTest {

    public static void main(String[] args) {
//        biggerThan2Power();
//        StringUtils.divisionLine();
        twoPower(0);
        StringUtils.divisionLine();
    }
    private static final int MAXIMUM_CAPACITY = 1 << 30;
    private static void twoPower(int c){
        log.info("c {}", c);
//        int n = c - 1;
        int n = c;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        log.info("result {}", (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1);
    }

    public static void biggerThan2Power(){
        long val = 9999999999L;
        long a = 1;
        while( a < val){
            a = a << 1;
        }
        System.out.println(a);
        System.out.println(Long.toBinaryString(a));
        System.out.println(Long.toHexString(a));
    }

}
