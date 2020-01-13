package com.seu.typestatic.Controller;


import com.seu.typestatic.POJO.Type;
import com.seu.typestatic.Service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TypeController {
    @Autowired
    private TypeService typeService;

    @RequestMapping("/getTypeData")
    public List<Type> getHumidityData() {
        return typeService.getTypeData();
    }

    @RequestMapping("/setType")
    public void setType(@RequestBody Type type){
        typeService.insertNode(type.getType(),type.getNumber());
    }
}
