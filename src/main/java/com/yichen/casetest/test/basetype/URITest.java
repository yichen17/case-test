package com.yichen.casetest.test.basetype;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/6/6 14:24
 * @describe URI 测试
 */
@Slf4j
public class URITest {

    public static void main(String[] args) {

//        urlFieldTest();
//        StringUtils.divisionLine();
        variableReplace();

    }


    private static void variableReplace(){
        log.info("replaceMacrosFromConfig => {}", replaceMacrosFromConfig("${name} => ${age} => ${sex}",
                new HashMap<String, String>(){
            {
                put("name", "yichen");
                put("age", "18");
                put("sex", "boy");
            }
        }));
    }

    /**
     *   区别点：
     *      .*    贪婪匹配，到底
     *      .*?   非贪婪匹配，只匹配一次
     *      .*+   不能用
     *
     *      .   用来匹配出换行符\n以外的任意字符
     *      *   用来匹配前面的子表达式任意次
     *      +   用来匹配前面的子表达式一次或多次(大于等于1次）
     *      ?   用来匹配前面的子表达式零次或一次
     *
     */
    private static final Pattern VAR_PATTERN = Pattern.compile("\\$\\{(.*?)\\}");
    private static String replaceMacrosFromConfig(String macro, Map<String, String> dictionary) {
        String result = macro;
        Matcher matcher = VAR_PATTERN.matcher(result);
        while (matcher.find()) {
            String key = matcher.group(1);
            String value = dictionary.get(key);
            if (value != null) {
                result = result.replaceAll("\\$\\{" + key + "\\}", value);
                matcher = VAR_PATTERN.matcher(result);
            }
        }
        return result.trim();
    }


    private static void urlFieldTest(){
        //        String s = "redis://user:password@example.com:6379";
        String s= "https://wxtest.9fbank.com/h5/#/billList?token=ef1503005bb401002e505dca00e04b498b4ae8e1c7573906";
        try {
            URI uri = new URI(s);
            log.info("{} {}", uri.getAuthority(), uri.getHost());
            log.warn("analysis uri {}", JSON.toJSONString(uri));
            uri = new URI("http://192.168.0.1:7421/test/hello");
            log.info("{} {}", uri.getAuthority(), uri.getHost());
            log.warn("analysis uri {}", JSON.toJSONString(uri));
        }
        catch (URISyntaxException e){
            log.error("{}", e.getMessage(), e);
        }
    }

}
