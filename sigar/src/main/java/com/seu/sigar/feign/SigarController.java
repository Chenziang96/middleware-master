package com.seu.sigar.feign;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "sigar")
public interface SigarController {

    @GetMapping(path = "/sigarSys")//获取系统信息
    JSONObject getSystemInf();

    @GetMapping(path = "/invoke/{value}")//获取调用链信息
    String invoke(@PathVariable(value = "value") String value);

    @GetMapping(path = "/cpuGraph")//获取cpu信息
    String[] getCpuGraph();

    @GetMapping(path = "/ramGraph")//获取ram信息
    Long[] getRamGraph();
}