package com.yichen.casetest.test.regex;

import com.yichen.casetest.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2021/12/8 10:13
 * @describe 正则表达式测试
 */
public class RegexTest {


    public static void main(String[] args) {
        test();
        StringUtils.divisionLine();
        test1();
    }

    private static final Pattern jsonSelect = Pattern.compile("\"comparison_type\":\"(\\d+)\"");
    private static void test1(){
        String s = "{\"attack_result\":{\"result\":false,\"score\":7.045269E-5,\"threshold\":0.5},\"biz_no\":\"\",\"comparison_type\":\"0\",\"device_risk_info\":{\"device_info_level\":\"0\",\"device_info_tags\":{\"is_hook\":0,\"is_injection\":0,\"is_root\":0,\"is_virtual_environment\":0}},\"images\":{\"image_best\":\"\"},\"request_id\":\"1687862649,27d95008-9ee3-40ac-899a-c2ffd7acf826\",\"result_code\":1000,\"result_message\":\"SUCCESS\",\"time_used\":357,\"user_faced_time\":\"1687862648\",\"verification\":{\"idcard\":{\"confidence\":-3.8044045,\"thresholds\":{\"1e-3\":62.169,\"1e-4\":69.315,\"1e-5\":74.399,\"1e-6\":78.038}}},\"video_key\":\"oxqPlHzBb7aUQSX4y1sSYTYaOzhqb7Hy\"}";
        Matcher matcher = jsonSelect.matcher(s);
        while (matcher.find()){
            System.out.println(matcher.group(1));
        }
    }

    private static final Pattern QUERY_STRING_PATTERN = Pattern.compile("(?<!\\{)\\?");
    private static final Pattern regex = Pattern.compile("(\\$\\{[^\\}]+})");

    private static void test(){
        try{
            int pos=0;
            List<String> list = new ArrayList<>(10);
            //    [^//}]+  表示一个或多个 不是 } 的    中括号内 ^ 表示取反。

            Matcher m = regex.matcher("'${capitalType}'!='9F'&&${tenantId}==1001");
            while (m.find()) {
                String res=m.group();
                System.out.println(pos+res);
                list.add(res.substring(2, m.group().length() - 1));
            }
            System.out.println(list);
            String s = "/test?name=yichen";
            Matcher matcher1 = QUERY_STRING_PATTERN.matcher(s);
            while (matcher1.find()){
                System.out.println(s.substring(0, matcher1.start()));
            }

        }
        catch (Exception e){
            System.out.println(e.getCause());
        }
    }

}
