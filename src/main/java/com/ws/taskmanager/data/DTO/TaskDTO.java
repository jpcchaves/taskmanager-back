package com.ws.taskmanager.data.DTO;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TaskDTO {

  @NotBlank
  private String task;

  @FutureOrPresent
  private LocalDateTime deadline;

  @NotNull
  private Boolean concluded;
  private LocalDateTime createdAt;

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public String getTask() {
    return task;
  }

  public void setTask(String task) {
    this.task = task;
  }

  public LocalDateTime getDeadline() {
    return deadline;
  }

  public void setDeadline(LocalDateTime deadline) {
    this.deadline = deadline;
  }

  public Boolean getConcluded() {
    return concluded;
  }

  public void setConcluded(Boolean concluded) {
    this.concluded = concluded;
  }

  @Override
  public String toString() {
    return "TaskDTO{" +
            "task='" + task + '\'' +
            ", deadline=" + deadline +
            ", concluded=" + concluded +
            '}';
  }
}
