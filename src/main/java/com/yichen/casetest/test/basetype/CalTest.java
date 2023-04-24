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
        biggerThan2Power();
        StringUtils.divisionLine();

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
