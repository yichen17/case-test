package com.yichen.casetest.test;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2023/6/6 21:48
 * @describe 令人惊讶的事
 */
@Slf4j
public class AmazingThings {

    public static void main(String[] args)throws Exception {
        fieldEqualsTwoValue();
    }


    /**
     * 实现一   Integer为包装类型， -128 到 127 之间会做缓存，这里是通过反射修改了缓存
     *   另一个在test包目录下面
     * 参考文章： https://mp.weixin.qq.com/s/eLAvUnjmUtW50njiVUiW9g
     * @throws Exception
     */
    private static void fieldEqualsTwoValue()throws Exception{
        Class<?> cache = Integer.class.getDeclaredClasses()[0];
        Field c = cache.getDeclaredField("cache");
        c.setAccessible(true);
        Integer[] array = (Integer[]) c.get(cache);
        array[130] = array[129];
        array[131] = array[129];
        int a =  1;
        if (a == (Integer)1 && a == (Integer)2 && a == (Integer)3){
            log.info("success");
        }

        log.info("{} {} {}", new Integer("1"), new Integer("2"), new Integer("3"));
        log.info("{} {} {}", new Integer(1), new Integer(2), new Integer(3));
        log.info("{} {} {}", new Integer(1).equals(1),new Integer(2).equals(1), new Integer(3).equals(1));
        log.info("{} {}", new Integer(1).equals(new Integer(2)), new Integer(1).equals(new Integer(3)));
    }






}
