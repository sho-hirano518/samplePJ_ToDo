package sample.common.dao.entity;

import java.time.LocalDateTime;

public class Login {

	 private Integer taskId; // ToDoのID
	 private String username; // ユーザー名
	 private String password; // パスワード
	 private LocalDateTime createdTime; // 作成日時
	 private LocalDateTime updatedTime; // 更新日時

//	 Getter
	 public Integer getTaskId() {return taskId;}
	 public String getUsername() {return username;}
	 public String getPassword() {return password;}
	 public LocalDateTime getCreatedTime() {return createdTime;}
	 public LocalDateTime getUpdatedTime() {return updatedTime;}
	 
//	 Setter
	 public void setTaskId(Integer taskId) {this.taskId = taskId;}
	 public void setUsername(String username) {this.username = username;}
	 public void setPassword(String password) {this.password = password;}
	 public void setCreatedTime(LocalDateTime createdTime) {this.createdTime = createdTime;}
	 public void setUpdatedTime(LocalDateTime updatedTime) {this.updatedTime = updatedTime;}
}
