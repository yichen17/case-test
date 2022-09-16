package com.yichen.casetest.test.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/9/15 10:15
 * @describe 字符串正则切分研究
 */
public class StringRegex {
    public static void main(String[] args) {
        String addr = "狂杀一条街";
        String district = "";
//        String regex3="(?<district>[^市]+市|[^县]+县|[^旗]+旗|.+区)";
//        String regex3="(?<district>[^市]+市)";  //匹配再加市
        String regex3="(?<city>[^辖区]+辖区|[^盟]+盟|[^自治州]+自治州|[^地区]+地区|[^市]+市|.+区划)";
        Matcher m3 = Pattern.compile(regex3).matcher(addr);
        while (m3.find()){
            district = m3.group("city");
            if (district != null){
                addr=addr.replaceAll(district,"");
                break;
            }
        }
        System.out.println(district);
    }
}
