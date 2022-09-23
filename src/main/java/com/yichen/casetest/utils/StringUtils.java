package com.yichen.casetest.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private static final String[] SPECIAL_CITY = {"北京市", "上海市", "天津市", "重庆市"};
    private static final Pattern PROVINCE_REGEX = Pattern.compile("(?<province>[^自治区]+自治区|[^省]+省|[^市]+市)");
    private static final Pattern CITY_REGEX = Pattern.compile("(?<city>[^辖区]+辖区|[^盟]+盟|[^自治州]+自治州|[^地区]+地区|[^市]+市|.+区划)");
    /**
     * 区正则，区只需要截取一个
     */
    private static final Pattern DISTRICT_REGEX = Pattern.compile("(?<district>[^市]+市|[^县]+县|[^旗]+旗|[^区]+区)");

    /**
     * 参考 https://blog.csdn.net/weixin_43703157/article/details/87340629
     * @param addr 待解析地址
     *                replaceAll  避免 OCR 识别出来的数据重复
     * @return
     */
    public static Map<String,String> getAddress(String addr){
        Map<String,String> map = new HashMap(8);
        String city =null;
        String province = null;
        String district=null;
        Matcher m1 = PROVINCE_REGEX.matcher(addr);
        while (m1.find()){
            province = m1.group("province");
            // 如果有市这个关键字，且非4个直辖市，忽略
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(province) && province.contains("市") && !Arrays.asList(SPECIAL_CITY).contains(province)){
                province = "";
                break;
            }
            if(province != null){
                addr=addr.replaceAll(province,"");
                break;
            }
        }
        Matcher m2 = CITY_REGEX.matcher(addr);
        while (m2.find()){
            city = m2.group("city");
            if (city != null){
                addr=addr.replaceAll(city,"");
                break;
            }
        }
        Matcher m3 = DISTRICT_REGEX.matcher(addr);
        while (m3.find()){
            district = m3.group("district");
            if (district != null){
                addr=addr.replaceAll(district,"");
                break;
            }
        }
        map.put("province",province);
        map.put("city",city);
        map.put("district",district);
        map.put("address",addr);
        return map;
    }

}
