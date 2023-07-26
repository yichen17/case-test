package com.yichen.casetest.test.fastjon.genericTrans;

import com.alibaba.fastjson.TypeReference;
import com.yichen.casetest.utils.FastJsonUtils;
import com.yichen.casetest.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Qiuxinchao
 * @date 2023/7/20 14:28
 * @describe   string 泛型转换
 *      https://blog.csdn.net/qq_42896721/article/details/105091807
 *      https://blog.csdn.net/xdx_dili/article/details/125560036
 *
 *  资料
 *      http://en.wikipedia.org/wiki/Generics_in_Java#Type_erasure
 *      http://gafter.blogspot.com/2006/12/super-type-tokens.html
 *      https://docs.oracle.com/javase/tutorial/java/generics/bounded.html
 *
 *  概念准则：
 *  1、上边界和下边界划分  https://docs.oracle.com/javase/tutorial/java/generics/wildcardGuidelines.html
 *      1） in 读取数据，也就是拷贝数据源用 extends
 *      2） out 写数据，也就是存储数据 super
 *      3）同时在 in out 中是，不推荐使用通配符
 *
 *   特殊场景：
 *   1、List<?> 只能插入 null 值
 *   2、List<Integer>  不是  List<Number> 的子类
 *   3、
 *
 *   思考问题：
 *   1、https://docs.oracle.com/javase/tutorial/java/generics/restrictions.html
 *   2、
 *
 */
@Slf4j
public class GenericTransTest {
    private static final String s = "{\"name\":\"yichen\",\"age\":\"18\",\"goods\":{\"goodsName\":\"海底两万里\",\"goodsPrice\":12.34,\"goodsWeight\":\"2kg\"}}";
    private static final String ss = "{\"name\":\"yichen\",\"age\":\"18\",\"goods\":{\"goodsName\":\"海底两万里\",\"goodsPrice\":12.34,\"goodsWeight\":\"2kg\"}}";
    private static final String sss = "{\"goodsName\":\"海底两万里\",\"goodsPrice\":12.34,\"goodsWeight\":\"2kg\"}";

    public static void main(String[] args) {
        testDirect();
//        Man<Object> trans = trans(s);
//        Man<List<Man.GoodsInfo>> other = other(ss);
//        Man.GoodsInfo trans1 = simpleTrans(sss);


//        Man.GoodsInfo goodsInfo = FastJsonUtils.fromJson(sss, new TypeReference<Man.GoodsInfo>() {
//        });

        // 泛型单纯测试
        print(1);
        print(1.0);
        printList(Arrays.asList(1, "2", "5", "2", 1.0));
        StringUtils.divisionLine();
        printListGeneric(Arrays.asList(1, "2", "5", "2", 1.0));
        List<?> list = new ArrayList<>();
        list.add(null);

        StringUtils.divisionLine();

//        List<EvenNumber> le = new ArrayList<>();
//        List<? extends NaturalNumber> ln = le;
//        ln.add(new NaturalNumber(35));  // compile-time error



        log.info("999");
    }


    private static  <T> Man<List<T>> other(String s){
        return FastJsonUtils.fromJson(s, new TypeReference<Man<List<T>>>(List.class) {
        });
    }

    private static  <T> Man<T> trans(String s){
        return FastJsonUtils.fromJson(s, new TypeReference<Man<T>>() {
        });
    }

    private static  <T>  T simpleTrans(String rr){
        return FastJsonUtils.fromJson(rr, new TypeReference<T>() {
        });
    }

    private static void testDirect(){

        Man<List<Man.GoodsInfo>> result = FastJsonUtils.fromJson(s, new TypeReference<Man<List<Man.GoodsInfo>>>() {
        });

        log.info("end end end");
    }

    private static <T extends Number> void print(T number){
        log.info("print => {}", number);
    }

    /**
     * 这种只能指定 List<Object> , List<Integer>之类的编译不通过
     * @param list
     */
    private static void printList(List<Object> list){
        for (Object object : list){
            System.out.println(object);
        }
    }

    private static void printListGeneric(List<?> list){
        for (Object object : list){
            System.out.println(object);
        }
    }

    // 牛逼案例   https://docs.oracle.com/javase/tutorial/java/generics/capture.html

    private static void foo(List<?> i){
//        i.set(0, i.get(0));
//        fooHelper(i);
    }

    private static <T> void fooHelper(List<T> l){
        l.set(0, l.get(0));
    }

    static class NaturalNumber {

        private int i;

        public NaturalNumber(int i) { this.i = i; }
    }

    static class EvenNumber extends NaturalNumber {

        public EvenNumber(int i) { super(i); }
    }


}
