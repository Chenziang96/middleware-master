package seu.example.hibernateclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seu.example.hibernateclient.dao.TypeDao;
import seu.example.hibernateclient.entity.Type;

import java.util.List;

@RestController
public class TypeController {
    @Autowired
    private TypeDao typeDao;

    @RequestMapping("/type/getTypeList")
    public List<Type> getTypeFile() {
        return typeDao.getTypeList();
    }

    @RequestMapping("/type/update")
    public void updateNode(@RequestBody Type type) {
        typeDao.updateNumber(type.getType(), type.getNumber());
    }

    @RequestMapping("/type/insert")
    public void insertNode(@RequestBody Type type) {
        typeDao.insertNumber(type.getType(), type.getNumber());
    }

    @RequestMapping("/type/getTypeByName")
    public List<Type> getType( String name) {
        return typeDao.getTypeByName(name);
    }
}
