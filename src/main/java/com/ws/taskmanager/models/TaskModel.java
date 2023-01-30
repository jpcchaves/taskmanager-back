package com.ws.taskmanager.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "_task")
public class TaskModel implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "task", nullable = false)
  private String task;

  @Column(name = "deadline", nullable = false)
  private LocalDateTime deadline;

  @Column(name = "is_concluded", nullable = false)
  private Boolean isConcluded;

  public TaskModel() {
  }

  public TaskModel(UUID id, String task, LocalDateTime deadline, Boolean isConcluded) {
    this.id = id;
    this.task = task;
    this.deadline = deadline;
    this.isConcluded = isConcluded;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
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
    return isConcluded;
  }

  public void setConcluded(Boolean concluded) {
    isConcluded = concluded;
  }
}
