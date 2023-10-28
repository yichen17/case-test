package com.yichen.casetest.test.regex;

import com.yichen.casetest.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/9/15 14:48
 * @describe 个人测试
 */
@Slf4j
public class StringRegexTest {
    public static void main(String[] args) throws Exception {
        test1();
        StringUtils.divisionLine();
        test();
        StringUtils.divisionLine();
        test2();
        StringUtils.divisionLine();
        test3();
        StringUtils.divisionLine();
        test4();
        StringUtils.divisionLine();
        test5();
    }

    private static void test1() {
        String addr = "狂杀一条街";
        String street = "";
        String regex3="(?<street>.+街)";
        Matcher m3 = Pattern.compile(regex3).matcher(addr);
        while (m3.find()){
            street = m3.group("street");
            if (street != null){
                addr=addr.replaceAll(street,"");
                break;
            }
        }
        System.out.println(street);
    }

    private static void test() throws Exception{
        Pattern compile = Pattern.compile("(?<!\\{)\\?");
        String s = "/test/requestParamTest";
        Matcher matcher = compile.matcher(s);
        log.info("matcher {}", matcher.find());
    }

    private static void test2() throws Exception{
        Pattern compile = Pattern.compile("(123|456){2}");
        String s = "456123";
        Matcher matcher = compile.matcher(s);
        log.info("matcher {}", matcher.find());
    }

    private static void test3() throws Exception{
        Pattern compile = Pattern.compile("a{1,3}");
        String s = "aaaa";
        Matcher matcher = compile.matcher(s);
        log.info("matcher {} {} {}", matcher.find(), matcher.start(), matcher.groupCount());
    }


    private static void test4() throws Exception {
        log.info("{}", "aaa".matches("a{1,3}"));
        //    引用的语法是\数字，数字代表引用前面第几个捕获分组，注意非捕获分组不能被引用   匹配 XML
        log.info("{}", "<span></span>".matches("<([a-z]+)><\\/\\1>"));
        // 忽略大小写
        log.info("{} {}", "asd".matches("(?i)ASD"), "asd".matches("Asd"));
    }

    private static final Pattern numberCheck = Pattern.compile("^[0-9]+$");
    private static void test5(){
        System.out.println(numberCheck.matcher("123").matches());
        System.out.println(numberCheck.matcher("123X").matches());
        System.out.println(numberCheck.matcher("123X22").matches());
        System.out.println(numberCheck.matcher("ab").matches());
    }




}
