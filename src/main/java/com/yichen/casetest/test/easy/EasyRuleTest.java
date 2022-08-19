package com.yichen.casetest.test.easy;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

import java.util.Optional;

/**
 * @author Qiuxinchao
 * @version 1.0
 * @date 2022/8/19 9:21
 * @describe 测试demo
 * =>  https://github.com/j-easy/easy-rules
 */
public class EasyRuleTest {

    public static void main(String[] args) {
        // define facts
        Facts facts = new Facts();
        facts.put("rain", true);

        // define rules
        WeatherRule weatherRule = new WeatherRule();
        Rules rules = new Rules();
        rules.register(weatherRule);

        // fire rules on known facts
        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);

    }
}
