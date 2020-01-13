package com.seu.typestatic.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.net.HttpHeaders;
import com.google.common.net.MediaType;
import com.seu.typestatic.JSONUtils;
import com.seu.typestatic.POJO.Type;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService{
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Type> getTypeData(){
        return restTemplate.getForObject("http://localhost:9014/type/getTypeList",List.class);
    }

    @Override
    public void insertNode(String type,int number){
        String TypeList = restTemplate.getForObject("http://localhost:9014/type/getTypeByName?name={1}",String.class,type);
        System.out.println(TypeList);
        List<Type> tempList = JSON.parseArray(TypeList, Type.class);
        Type temp = tempList.get(0);
        if(temp.getNumber()!= 0){
            int tempNumber = temp.getNumber();
            number+=tempNumber;
            temp.setNumber(number);
            restTemplate.put("http://localhost:9014/type/update",temp);
        }else{
            restTemplate.put("http://localhost:9014/type/insert",temp);
        }
//        System.out.println(TypeList.get(0));
//        System.out.println(TypeList.get(0) instanceof Type);
//        System.out.println(TypeList.get(0).getType());
//        Type temp = JSONUtils.string2Obj(TypeList.get(0),Type);
//        Type temp = TypeList.get(0);
//        System.out.println(temp);
//        Type type1 = new Type();
//        type1.setNumber(number);
//        type1.setType(type);
//        System.out.println(type1.getNumber());
//        System.out.println(temp.getNumber());
//        if(temp.getNumber()!= 0){
//            int tempNumber = temp.getNumber();
//            number+=tempNumber;
//            temp.setNumber(number);
//            restTemplate.put("http://localhost:9014/type/update",temp);
//        }else{
//            restTemplate.put("http://localhost:9014/type/insert",type1);
//        }

    }
}
