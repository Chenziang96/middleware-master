package com.seu.typestatic.Service;

import com.seu.typestatic.POJO.Type;

import java.util.List;

public interface TypeService {

    public List<Type> getTypeData();

    public void insertNode(String type,int number);
}
