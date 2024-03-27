package com.yichen.casetest.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * @author banYu
 * @version 1.0
 * @date 2024/3/27 08:39
 * @describe 数组相关工具类
 */
@Slf4j
public class ArrayUtils {

    public static int[][] same(int size, int from, int to){
        int[][] result = new int[size][2];
        int pos=0;
        for (int i=from; i<=to; i++){
            result[pos++] = new int[]{i, i};
        }
        log.debug("ArrayUtils#same:{}", JSON.toJSONString(result));
        return result;
    }

}
