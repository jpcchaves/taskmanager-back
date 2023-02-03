package com.ws.taskmanager.data.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonPropertyOrder({"id", "task", "concluded", "deadline", "createdAt"})
public class TaskCreateDTO extends RepresentationModel<TaskResponseDTO> implements Serializable {

    @JsonProperty("id")
    @Mapping("id")
    private UUID key;

    @NotBlank
    private String task;

    @FutureOrPresent
    private LocalDateTime deadline;


    public UUID getKey() {
        return key;
    }

    public void setKey(UUID key) {
        this.key = key;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        TaskCreateDTO that = (TaskCreateDTO) o;

        if (!getKey().equals(that.getKey())) {
            return false;
        }
        if (!getTask().equals(that.getTask())) {
            return false;
        }
        return getDeadline().equals(that.getDeadline());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getKey().hashCode();
        result = 31 * result + getTask().hashCode();
        result = 31 * result + getDeadline().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TaskCreateDTO{" +
                "key=" + key +
                ", task='" + task + '\'' +
                ", deadline=" + deadline +
                '}';
    }
}
