package com.seu.sigar.feign;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.nepxion.discovery.common.constant.DiscoveryConstant;
import com.seu.sigar.impl.SigarRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;


@RestController
@ConditionalOnProperty(name = DiscoveryConstant.SPRING_APPLICATION_NAME, havingValue = "sigar")
public class SigarImpl extends AbstractFeignImpl implements SigarController{

    private static final Logger LOG = LoggerFactory.getLogger(SigarImpl.class);

    @Override
    @SentinelResource(value = "sigarInvoke", blockHandler = "handleBlock", fallback = "handleFallback")
    public String invoke(@PathVariable(value = "value") String value) {
//        initFlowQpsRule();
        value = doInvoke(value);
        LOG.info("调用路径：{}", value);
        return value;
    }

    private SigarRes sigarRes = new SigarRes();
    public JSONObject jsContainer = sigarRes.jsonContainer;

    @Override
    @SentinelResource(value = "sigarSystem", blockHandler = "handleBlock", fallback = "handleFallback")
    public JSONObject  getSystemInf(){
        sigarRes.run();
        jsContainer = sigarRes.jsonContainer;
        System.out.println("json"+jsContainer);
        return jsContainer ;
    }

    @Override
    @SentinelResource(value = "cpuGraph", blockHandler = "handleBlock", fallback = "handleFallback")
    public String[]  getCpuGraph(){
        sigarRes.runCpu();
        String cpu[] = sigarRes.cpuIn;
        return cpu;
    }


    @Override
    @SentinelResource(value = "ramGraph", blockHandler = "handleBlock", fallback = "handleFallback")
    public Long[]  getRamGraph(){
        sigarRes.runRam();
        Long ram[] = sigarRes.ramIn;
        return ram;
    }


    public String handleBlock(String value, BlockException e) {
        return value + "-> A server sentinel block, cause=" + e.getClass().getName() + ", rule=" + e.getRule() + ", limitApp=" + e.getRuleLimitApp();
    }

    public String handleFallback(String value) {
        return value + "-> A server sentinel fallback, from AFeign";
    }



}
