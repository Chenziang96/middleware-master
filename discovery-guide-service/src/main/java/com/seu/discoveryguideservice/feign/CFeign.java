package com.seu.discoveryguideservice.feign;

/**
 * <p>Title: Nepxion Discovery</p>
 * <p>Description: Nepxion Discovery</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 *
 * @author Haojun Ren
 * @version 1.0
 */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "discovery-guide-service-c")
public interface CFeign {
    //    @GetMapping(path = "/hi")
//    String sayHiFromClientC(@RequestParam(value = "name") String name);
    @GetMapping(path = "/invoke/{value}")
    String invoke(@PathVariable(value = "value") String value);
}