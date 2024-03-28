package com.yichen.casetest.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author banYu
 * @version 1.0
 * @date 2024/3/27 08:39
 * @describe 数组相关工具类
 */
@Slf4j
public class ArrayUtils {

    private static Random random = new Random();

    public static int[][] same(int size, int from, int to){
        int[][] result = new int[size][2];
        int pos=0;
        for (int i=from; i<=to; i++){
            result[pos++] = new int[]{i, i};
        }
        log.debug("ArrayUtils#same:{}", JSON.toJSONString(result));
        return result;
    }


    /**
     * 对于每个f[i]节点的值都  0<=f[i]<=i
     * @param len
     * @return
     */
    public static int[] posLessThen(int len){
        int[] result = new int[len];
        if (len == 1){
            return result;
        }
        for (int i=1; i<len; i++){
            result[i] = random.nextInt(i);
        }
        log.debug("ArrayUtils#posLessThen:{}", JSON.toJSONString(result));
        return result;
    }

}
