package com.yichen.casetest.test.fastjon.genericTrans;

import com.alibaba.fastjson.TypeReference;
import com.yichen.casetest.utils.FastJsonUtils;
import lombok.extern.slf4j.Slf4j;

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


}
