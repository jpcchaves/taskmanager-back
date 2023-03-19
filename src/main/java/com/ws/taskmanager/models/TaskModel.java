package com.ws.taskmanager.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "_task")
public class TaskModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    @NotBlank(message = "A task é obrigatória!")
    private String task;

    @Column(nullable = false)
    @FutureOrPresent(message = "O prazo deve ser a data atual ou uma data futura.")
    private LocalDateTime deadline;

    @Column(nullable = false)
    @NotNull(message = "A situação da task é obrigatória!")
    private Boolean concluded;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    public TaskModel() {
    }

    public TaskModel(UUID id,
                     String task,
                     LocalDateTime deadline,
                     Boolean concluded,
                     LocalDateTime createdAt,
                     UserModel user) {
        this.id = id;
        this.task = task;
        this.deadline = deadline;
        this.concluded = concluded;
        this.createdAt = createdAt;
        this.user = user;
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
        return concluded;
    }

    public void setConcluded(Boolean concluded) {
        this.concluded = concluded;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserModel getUserModel() {
        return user;
    }

    public void setUserModel(UserModel userModel) {
        this.user = userModel;
    }
}

