package com.yichen.casetest.test.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Qiuxinchao
 * @date 2023/4/21 8:55
 * @describe sentinel简单demo
 */
@Slf4j
public class SentinelTest {

    private static void initFlowRules(){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(20);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

    public static void main(String[] args) throws Exception {
        initFlowRules();
        int errorCount = 0;
        while (true) {
            if (errorCount > 20){
                break;
            }
            Entry entry = null;
            try {
                entry = SphU.entry("HelloWorld");
                /*您的业务逻辑 - 开始*/
                log.info("hello world");
                /*您的业务逻辑 - 结束*/
            }
            catch (BlockException e1) {
                /*流控逻辑处理 - 开始*/
                log.error("block error {}", e1.getMessage(), e1);
                /*流控逻辑处理 - 结束*/
                errorCount++;
            }
            catch (Exception e){
                log.error("一般异常{}", e.getMessage(), e);
                errorCount++;
            }
            finally {
                if (entry != null) {
                    entry.exit();
                }
            }
        }

    }

}
