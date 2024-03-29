package com.yichen.casetest.test.basetype;

import com.yichen.casetest.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/11/23 10:26
 * @describe
 */
@Slf4j
public class StringTest {

    public static void main(String[] args) {
        matchesTest();
        StringUtils.divisionLine();
        splitTest();
        StringUtils.divisionLine();
        escape();
        StringUtils.divisionLine();
        byteToStream();
        StringUtils.divisionLine();
        buildTest();
//        StringUtils.divisionLine();
//        forceTransform();
        StringUtils.divisionLine();
        indexSubstring();
        StringUtils.divisionLine();
        indexTest();
        StringUtils.divisionLine();
        strCompare();
    }

    /**
     * 字符串比较逻辑
     * 先比较每一位字符，有不同结束，如果有效长度内字符串都一样，则比对两者的长度
     */
    private static void strCompare(){
        String p = "1";
        String q = "10";
        System.out.println(p.compareTo(q));
        q = "11";
        System.out.println(p.compareTo(q));
    }


    private static void buildTest(){
        StringBuilder builder = new StringBuilder();
        builder.insert(0, 'a');
        builder.insert(0, 'b' );
        log.info("builder {}", builder);
    }

    private static void matchesTest(){
        log.info("{}", "com.yichen".matches("com.shanliang"));
        log.info("{}", "com.yichen".matches("com.yichen"));
    }

    private static void escape(){
        String s = "{\\\"mobile\\\":\\\"15652786995\\\",\\\"registrationId\\\":\\\"170976fa8af0228f9ae\\\",\\\"userData\\\":\\\"{\\\\\\\"android_id\\\\\\\":\\\\\\\"faf1bde8b282a8f3\\\\\\\",\\\\\\\"app_name\\\\\\\":\\\\\\\"万卡\\\\\\\",\\\\\\\"battery_level\\\\\\\":\\\\\\\"59%\\\\\\\",\\\\\\\"boot_times\\\\\\\":\\\\\\\"2023-01-03 10:50:22\\\\\\\",\\\\\\\"carrier\\\\\\\":\\\\\\\"NULL\\\\\\\",\\\\\\\"devicetype\\\\\\\":\\\\\\\"android\\\\\\\",\\\\\\\"iccid\\\\\\\":\\\\\\\"NULL\\\\\\\",\\\\\\\"imei\\\\\\\":\\\\\\\"NULL\\\\\\\",\\\\\\\"imsi\\\\\\\":\\\\\\\"NULL\\\\\\\",\\\\\\\"ip\\\\\\\":\\\\\\\"10.203.0.207\\\\\\\",\\\\\\\"latitude\\\\\\\":\\\\\\\"39.915168\\\\\\\",\\\\\\\"longitude\\\\\\\":\\\\\\\"116.403875\\\\\\\",\\\\\\\"mac\\\\\\\":\\\\\\\"70:5E:55:9A:38:07\\\\\\\",\\\\\\\"nettype\\\\\\\":\\\\\\\"WIFI\\\\\\\",\\\\\\\"oaid\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"os_name\\\\\\\":\\\\\\\"android\\\\\\\",\\\\\\\"os_version\\\\\\\":\\\\\\\"11\\\\\\\",\\\\\\\"resolution\\\\\\\":\\\\\\\"1080x2208\\\\\\\",\\\\\\\"version\\\\\\\":\\\\\\\"4.1.5\\\\\\\",\\\\\\\"wifimac\\\\\\\":\\\\\\\"02:00:00:00:00:00\\\\\\\",\\\\\\\"udid\\\\\\\":\\\\\\\"13323254354\\\\\\\"}\\\",\\\"securityType\\\":\\\"owner\\\",\\\"loginType\\\":\\\"1\\\",\\\"system_environment\\\":\\\"1\\\",\\\"dataMap\\\":{},\\\"checkPrivacyPolicy\\\":\\\"1\\\",\\\"hasReadPrivacyPolicy\\\":\\\"1\\\",\\\"privacyPolicyName\\\":\\\"\\\",\\\"user\\\":\\\"15652786995\\\",\\\"pwd\\\":\\\"cXdlMTIz\\\"}";
        log.info("{}", StringEscapeUtils.unescapeJava(s));
    }

    private static void splitTest(){
        String s = "2022-12-29 13:55:00;2022-12-29 14:00:00|2022-12-30 13:55:00;2022-12-31 14:00:00";
        int pos = s.indexOf("|");
        String[] items = s.split("\\|");
        log.info("{}", String.join(",", items));
        log.info("{}, {} {}", pos, s.substring(0, pos), s.substring(pos+1));

        String t = "1234";
        log.info("{}", t.substring(0, t.length()));

        String rr = "::boo:and:foo";
        String[] item = rr.split(":", 2);
        item = rr.split(":", 5);
        item = rr.split(":", -2);
        item = rr.split(":", 0);
        item = rr.split("o", 5);
        item = rr.split("o", -2);
        item = rr.split("o", 0);
        item = " ,".split(",");

    }

    private static void byteToStream(){
        byte[] bytes = new byte[]{39, 50, 48, 50, 51, 45, 48, 49, 45, 51, 48, 32, 48, 55, 58, 53, 54, 58, 48, 53, 46, 55, 49, 55, 39};
        log.info("{}", new String(bytes));
    }

    private static void forceTransform(){
        Object s = "6";
        log.info("force transform {}", (double)(Character)s);
    }


    private static void indexSubstring(){
        String s = "111T1H22";
        int pos = s.indexOf("H");
        log.info("{}", s.substring(0, pos +  1));
    }


    private static void indexTest(){
        String s = "a=1&b=2";
        log.info(" {} {} {} {}", s.indexOf("&"),  s.indexOf("&", 2),
                s.indexOf("&", 3), s.indexOf("&", 4));
    }

}
