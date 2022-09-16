package com.yichen.casetest.test.regex;

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
        try{
            int pos=0;
            List<String> list = new ArrayList<>(10);
            //    [^//}]+  表示一个或多个 不是 } 的    中括号内 ^ 表示取反。
            Pattern regex = Pattern.compile("(\\$\\{[^\\}]+})");
            Matcher m = regex.matcher("'${capitalType}'!='9F'&&${tenantId}==1001");
            while (m.find()) {
                String res=m.group();
                System.out.println(pos+res);
                list.add(res.substring(2, m.group().length() - 1));
            }
            System.out.println(list);
        }
        catch (Exception e){
            System.out.println(e.getCause());
        }
    }
}
