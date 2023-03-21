package com.ws.taskmanager.services;

import com.ws.taskmanager.data.DTO.*;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TaskService {
    TaskResponseDto createTask(TaskCreateDto taskDTO) throws Exception;

    TasksResponseDtoPaginated findByUserWithPagination(Pageable pageable);

    TaskResponseDto listTaskById(UUID id) throws Exception;

    TaskResponseDto updateTask(UUID id, TaskDto taskDTO) throws Exception;

    void deleteTask(UUID id);

    TaskPatchDto updateTaskSituation(UUID id, TaskPatchDto taskPatchDTO) throws Exception;
}
