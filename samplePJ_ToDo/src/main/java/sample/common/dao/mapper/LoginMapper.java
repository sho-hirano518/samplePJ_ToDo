package sample.common.dao.mapper;

import sample.common.dao.entity.Login;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface LoginMapper {
    List<Login> findAll();
    void insert(Login login);
    void delete(Integer taskId);
    void update(Login login);
	Login findByUsername(String username);
}