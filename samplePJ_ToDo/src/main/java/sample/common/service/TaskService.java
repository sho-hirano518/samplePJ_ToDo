package sample.common.service;

import sample.common.dao.entity.Task;
import java.util.List;

public interface TaskService {
    long countByUsername(String username);
    List<Task> findAll();
    List<Task> findList(String username, int page);
    void insert(Task task);
    void update(Task task);
    void delete(Integer taskId);
    Task findById(Integer id);
}