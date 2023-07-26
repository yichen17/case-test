package com.yichen.casetest.test.fastjon.genericTrans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Qiuxinchao
 * @date 2023/7/26 17:25
 * @describe  extends 和 super区别
 *       extends：指定的类或者子类
 *       super：指定的类或者超累
 *   参考文章：https://stackoverflow.com/questions/12604477/use-of-extends-and-super-in-collection-generics
 */
public class DemoTeset {

    public static void main(String[] args) {
//        List<EvenNumber> le = new ArrayList<>();
//        List<? extends NaturalNumber> ln = le;
//        le.add(new EvenNumber(7));
////        le.add(new NaturalNumber(35));
//        ln.add(new EvenNumber(7));
//        ln.add(new NaturalNumber(35));

//        List<? extends NaturalNumber> ln = new ArrayList<>();
//        ln.add(new EvenNumber(7));
//        ln.add(new NaturalNumber(35));

        // 为啥不行呢   Upper Bounded Wildcards  or Lower Bounded Wildcards

//        List<? extends Number> list = new ArrayList<>();
//        list.add(1);
//        list.add(1.0);
//        list.add(new BigDecimal("0"));


        List<? super Number> list11 = new ArrayList<>();
        list11.add(1);
        list11.add(1.0);
        list11.add(new BigDecimal("0"));

    }

    static class NaturalNumber {

        private int i;

        public NaturalNumber(int i) { this.i = i; }
    }

    static class EvenNumber extends NaturalNumber {

        public EvenNumber(int i) { super(i); }
    }

}
