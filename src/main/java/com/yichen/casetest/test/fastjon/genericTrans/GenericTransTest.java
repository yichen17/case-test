package com.yichen.casetest.test.fastjon.genericTrans;

import com.alibaba.fastjson.TypeReference;
import com.yichen.casetest.utils.FastJsonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author Qiuxinchao
 * @date 2023/7/20 14:28
 * @describe
 */
@Slf4j
public class GenericTransTest {
    private static final String s = "{\"name\":\"yichen\",\"age\":\"18\",\"goods\":{\"goodsName\":\"海底两万里\",\"goodsPrice\":12.34,\"goodsWeight\":\"2kg\"}}";
    private static final String ss = "{\"name\":\"yichen\",\"age\":\"18\",\"goods\":{\"goodsName\":\"海底两万里\",\"goodsPrice\":12.34,\"goodsWeight\":\"2kg\"}}";

    public static void main(String[] args) {
        testDirect();
        Man<Object> trans = trans(s);
        Man<List<Man.GoodsInfo>> other = other(ss);
        log.info("999");
    }


    private static  <T> Man<List<T>> other(String s){
        return FastJsonUtils.fromJson(s, new TypeReference<Man<List<T>>>() {
        });
    }

    private static  <T> Man<T> trans(String s){
        return FastJsonUtils.fromJson(s, new TypeReference<Man<T>>() {
        });
    }

    private static void testDirect(){

        Man<List<Man.GoodsInfo>> result = FastJsonUtils.fromJson(s, new TypeReference<Man<List<Man.GoodsInfo>>>() {
        });

        log.info("end end end");
    }


}
