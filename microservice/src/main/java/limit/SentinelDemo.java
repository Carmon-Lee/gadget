/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package limit;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liguang
 * @version SentinelDemo.java, v 0.1 2020年10月21日 14:19
 */
public class SentinelDemo {

    public static void main(String[] args) {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        // set limit qps to 20
        rule.setCount(5);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);


        for (int i = 0; i < 100; i++) {
            try (Entry entry = SphU.entry("HelloWorld")) {
                // Your business logic here.
                System.out.println("hello world");
            } catch (BlockException e) {
                // Handle rejected request.
                e.printStackTrace();
            }
        }


    }
}