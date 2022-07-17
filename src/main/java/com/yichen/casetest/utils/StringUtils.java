package com.yichen.casetest.utils;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/7/17 20:22
 * @describe 字符串工具类
 */
public class StringUtils {

    private final  static  String ALPHA_NUMBER = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * 从大小写英文字母和数字中构造随机字符串
     * @param len 随机字符串长度
     * @return 构造的随机字符串
     */
    public static String randomString(int len){
        StringBuilder builder = new StringBuilder();
        for (int i=0; i<len; i++){
            builder.append(ALPHA_NUMBER.charAt((int)(Math.random() * ALPHA_NUMBER.length())));
        }
        return  builder.toString();
    }

}
