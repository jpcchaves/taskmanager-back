package com.ws.taskmanager.data.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonPropertyOrder({"id", "task", "concluded", "deadline", "createdAt"})
public class TaskDto implements Serializable {

    @JsonProperty("id")
    private UUID id;

    @NotBlank(message = "A task é obrigatória!")
    private String task;

    @FutureOrPresent(message = "O prazo deve ser a data atual ou uma data futura.")
    private LocalDateTime deadline;

    @NotNull(message = "A situação da task é obrigatória!")
    private Boolean concluded;

    public TaskDto() {
    }

    public TaskDto(UUID id, String task, LocalDateTime deadline, Boolean concluded) {
        this.id = id;
        this.task = task;
        this.deadline = deadline;
        this.concluded = concluded;
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
}
