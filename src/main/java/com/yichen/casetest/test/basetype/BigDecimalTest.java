package com.yichen.casetest.test.basetype;

import com.yichen.casetest.utils.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/6/16 18:12
 * @describe
 */
public class BigDecimalTest {
    public static void main(String[] args) {
        baseTest();
        StringUtils.divisionLine();
        nonTerminatingDecimalExpansion();
        StringUtils.divisionLine();
    }


    /**
     *  无线数导致的
     */
    private static void nonTerminatingDecimalExpansion(){
        BigDecimal a = new BigDecimal("1000");
        a.divide(new BigDecimal("3000"));
    }

    private static void baseTest(){
        BigDecimal a = new BigDecimal("0.00");
        BigDecimal b = new BigDecimal("0");
        System.out.println(a.compareTo(b));
        Double d = 30.10;
        DecimalFormat df=new DecimalFormat(".##");
        System.out.println(df.format(d));

        System.out.println("".toLowerCase());
        BigDecimal c = new BigDecimal("100.21");
        System.out.println(c.toPlainString());
    }




}
