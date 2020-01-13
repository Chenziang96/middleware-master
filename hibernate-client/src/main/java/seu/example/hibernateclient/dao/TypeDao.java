package seu.example.hibernateclient.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import seu.example.hibernateclient.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;


public interface TypeDao extends JpaRepository<Type, String> {
    @Transactional
    @Modifying
    @Query(value = "insert into tb_type(type,number) values(?1,?2)", nativeQuery = true)
    public void insertNumber(String type, int number);

    @Transactional
    @Modifying
    @Query(value = "select * from tb_type", nativeQuery = true)
    public List<Type> getTypeList();

    @Transactional
    @Modifying
    @Query(value = "update `tb_type` set number=?2 where type=?1", nativeQuery = true)
    public void updateNumber(String type, int number);

    @Transactional
    @Modifying
    @Query(value = "select * from tb_type where type=?1", nativeQuery = true)
    public List<Type> getTypeByName(String name);

}
