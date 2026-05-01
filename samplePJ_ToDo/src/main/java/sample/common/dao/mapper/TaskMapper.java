package sample.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sample.common.dao.entity.Task;

@Mapper
public interface TaskMapper {
    long countByUsername(@Param("username") String username); // 件数用
    List<Task> findAll(@Param("username") String username, @Param("offset") int offset); // タスク中身
    void insert(Task task); // 追加用
    void update(Task task); // 更新用
    void delete(@Param("taskId") Integer id, @Param("username") String username); // 削除用
    Task findById(@Param("taskId") Integer id, @Param("username") String username); // 対象タスク選択用
}