package sample.common.dao.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {
	
	 private Integer taskId; // ToDoのID
	 private String username; // ユーザー名
	 private String title; // タイトル
	 private String content; // 内容
	 private String registName; // 登録者名
	 private LocalDate startDate; // 開始日
	 private LocalDate endDate; // 終了日
	 private LocalDateTime createdTime; // 作成日時
	 private LocalDateTime updatedTime; // 更新日時
	 
//	 Getter
	 public Integer getTaskId() {return taskId;}
	 public String getUsername() {return username;}
	 public String getTitle() {return title;}
	 public String getContent() {return content;}
	 public String getRegistName() {return registName;}
	 public LocalDate getStartDate() {return startDate;}
	 public LocalDate getEndDate() {return endDate;}
	 public LocalDateTime getCreatedTime() {return createdTime;}
	 public LocalDateTime getUpdatedTime() {return updatedTime;}
	 
//	 Setter
	 public void setTaskId(Integer taskId) {this.taskId = taskId;}
	 public void setUsername(String username) {this.username = username;}
	 public void setTitle(String title) {this.title = title;}
	 public void setContent(String content) {this.content = content;}
	 public void setRegistName(String registName) {this.registName = registName;}
	 public void setStartDate(LocalDate startDate) {this.startDate = startDate;}
	 public void setEndDate(LocalDate endDate) {this.endDate = endDate;}
	 public void setCreatedTime(LocalDateTime createdTime) {this.createdTime = createdTime;}
	 public void setUpdatedTime(LocalDateTime updatedTime) {this.updatedTime = updatedTime;}
	 
	 

}
