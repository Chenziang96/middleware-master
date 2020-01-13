package com.seu.microservicec.feign;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.nepxion.discovery.common.constant.DiscoveryConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@ConditionalOnProperty(name = DiscoveryConstant.SPRING_APPLICATION_NAME, havingValue = "discovery-guide-service-c")
public class HiImpl extends AbstractFeignImpl implements HiController{
    private static final Logger LOG = LoggerFactory.getLogger(HiController.class);

//    public static final String NAME = "microservice-c";
//    private static void initFlowQpsRule(){
//        // 定义热点限流的规则，对第一个参数设置 qps 限流模式，阈值为5
//        FlowRule rule = new FlowRule();
//        rule.setResource(NAME);
//        // 限流类型，qps
//        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
//        // 设置阈值
//        rule.setCount(2);
//        // 限制哪个调用方
//        rule.setLimitApp(RuleConstant.LIMIT_APP_DEFAULT);
//        // 基于调用关系的流量控制
//        rule.setStrategy(RuleConstant.STRATEGY_DIRECT);
//        // 流控策略
//        rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT);
//        FlowRuleManager.loadRules(Collections.singletonList(rule));
//    }

    @Override
    @SentinelResource(value = "microservice-c", blockHandler = "handleBlock", fallback = "handleFallback")
    public String invoke(@PathVariable(value = "value") String value) {
//        initFlowQpsRule();
        value = doInvoke(value);
        int iint = 0;
        for(int i=0;i<10000;i++){
            for(int j=0;j<10000;j++){
                iint = i*j;
            }
        }
        LOG.info("调用路径：{}", value);
        return value;
    }

    public String handleBlock(String value, BlockException e) {
        return value + "-> C server sentinel block, cause=" + e.getClass().getName() + ", rule=" + e.getRule() + ", limitApp=" + e.getRuleLimitApp();
    }

    public String handleFallback(String value) {
        return value + "-> C server sentinel fallback";
    }

}
