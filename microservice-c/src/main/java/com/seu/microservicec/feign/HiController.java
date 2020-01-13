package com.seu.microservicec.feign;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "discovery-guide-service-c")
public interface HiController {

    @GetMapping(path = "/invoke/{value}")
    String invoke(@PathVariable(value = "value") String value);
}
