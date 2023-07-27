package com.yichen.casetest.test.fastjon.genericTrans;

import java.util.List;

/**
 * @author Qiuxinchao
 * @date 2023/7/26 17:25
 * @describe  extends 和 super区别
 *       extends：指定的类或者子类
 *       super：指定的类或者超累
 *   参考文章：
 *      https://stackoverflow.com/questions/12604477/use-of-extends-and-super-in-collection-generics
 *      https://stackoverflow.com/questions/49926762/compilation-error-with-lower-bounded-wildcard
 */
public class DemoTeset {

    public static void main(String[] args) {
//        List<EvenNumber> le = new ArrayList<>();
//        List<? extends NaturalNumber> ln = le;
//        le.add(new EvenNumber(7));
////        le.add(new NaturalNumber(35));
//        ln.add(new EvenNumber(7));
//        ln.add(new NaturalNumber(35));




        /**
         * 为啥不行呢   Upper Bounded Wildcards  or Lower Bounded Wildcards
         * <? extends Number>
         *     参数化类型向上兼容
         *     只能用于读取，因为它无法确定写入的类型，它的强校验只是必须是 Number的子类，但是没有确定是哪个，它的子类有很多。但是对于读取没有限制
         * <? super Number>
         *     参数化类型向下兼
         *     只能用于写入，因为写入时可以确定是 Number的子类，而读取时无法确定到时是哪个子类，只能用Object接收
         */

        // C > B > A
//        List<B> list = new ArrayList<>();
//        List<? extends A> extendA = list;
//        List<? extends C> extendC = list;
//        List<? super C> superC = list;
//        List<? super A> superA = list;

//        List<? extends Number> list = new ArrayList<>();
//        list.add(1);
//        list.add(1.0);
//        list.add(new BigDecimal("0"));


//        List<? super Number> list11 = new ArrayList<>();
//        list11.add(1);
//        list11.add(1.0);
//        list11.add(new BigDecimal("0"));

    }

    static void printLower(List<? super Number> list){
        // 只能通过 Object接收而不能通过 Number
        for (Object item : list){
            System.out.println(item);
        }
    }

    static void printUpper(List<? extends  Number> list){
        for(Number item : list){
            System.out.println(item);
        }
    }

    static class NaturalNumber {

        private int i;

        public NaturalNumber(int i) { this.i = i; }
    }

    static class EvenNumber extends NaturalNumber {

        public EvenNumber(int i) { super(i); }
    }

    static class C extends B{

    }

    static class B extends A {

    }

    static class A {

    }

}
