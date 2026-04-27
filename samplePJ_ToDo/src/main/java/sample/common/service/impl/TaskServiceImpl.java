package sample.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.common.dao.entity.Task;
import sample.common.dao.mapper.TaskMapper;
import sample.common.service.TaskService;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired 
    private TaskMapper taskMapper;
    
    // 対象ユーザーのタスク件数カウント
    @Override
    public long countByUsername(String username) {
        return taskMapper.countByUsername(username);
    }
    
    // ページネーション対応タスク件数
    @Override
    public List<Task> findList(String username, int page) {
        int offset = (page - 1) * 5;
        return taskMapper.findAll(username, offset);
    }

    @Override
    public List<Task> findAll() {
        return taskMapper.findAll(null, 0);
    }

    // DB INSERT
    @Override
    @Transactional
    public void insert(Task task) {
        taskMapper.insert(task);
    }

    // DB UPDATE
    @Override
    @Transactional
    public void update(Task task) {
        taskMapper.update(task);
    }

    // DB DELETE
    @Override
    @Transactional
    public void delete(Integer taskId) {
        taskMapper.delete(taskId);
    }
    
    // １件取得用
    @Override
    public Task findById(Integer id) {
        return taskMapper.findById(id);
    }
}