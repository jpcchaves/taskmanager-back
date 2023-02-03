package com.ws.taskmanager.data.DTO;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TaskCreateDTO extends RepresentationModel<TaskCreateDTO> implements Serializable {
    @NotBlank
    private String task;

    @FutureOrPresent
    private LocalDateTime deadline;


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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TaskCreateDTO that = (TaskCreateDTO) o;

        if (!task.equals(that.task)) return false;
        return deadline.equals(that.deadline);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + task.hashCode();
        result = 31 * result + deadline.hashCode();
        return result;
    }
}
