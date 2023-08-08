package com.yichen.casetest.problem.notInject;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Qiuxinchao
 * @date 2023/8/8 17:20
 * @describe
 */
@Configuration
@ComponentScan(basePackages = {"com.yichen.casetest"},
        excludeFilters = {
//                @ComponentScan.Filter(type = FilterType.REGEX, pattern = {"[a-zA-z]+InjectService"}),
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = {"com.yichen.casetest.problem.notInject.*Service"}),
//                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {NotInjectService.class}),
        })
public class DevConfig {

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("[a-zA-z]+InjectService");
        Matcher repayFailConsumer = pattern.matcher("NotInjectService");
        while (repayFailConsumer.find()){
            System.out.println(repayFailConsumer.group());
        }
    }


}
