package com.ws.taskmanager.data.DTO;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.UUID;

public class TaskPatchDto implements Serializable {

    private UUID id;


    @NotNull(message = "A situação da task é obrigatória!")
    private Boolean concluded;

    public TaskPatchDto() {
    }

    public TaskPatchDto(UUID id, Boolean concluded) {
        this.id = id;
        this.concluded = concluded;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Boolean getConcluded() {
        return concluded;
    }

    public void setConcluded(Boolean concluded) {
        this.concluded = concluded;
    }
}
