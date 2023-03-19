package com.ws.taskmanager.services;

import com.ws.taskmanager.data.DTO.TaskCreateDto;
import com.ws.taskmanager.data.DTO.TaskDto;
import com.ws.taskmanager.data.DTO.TaskPatchDto;
import com.ws.taskmanager.data.DTO.TaskResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TaskService {
    TaskResponseDto createTask(TaskCreateDto taskDTO) throws Exception;

    Page<TaskResponseDto> listAllTasks(Pageable pageable);

    TaskResponseDto listTaskById(UUID id) throws Exception;

    TaskResponseDto updateTask(UUID id, TaskDto taskDTO) throws Exception;

    void deleteTask(UUID id);

    TaskPatchDto updateTaskSituation(UUID id, TaskPatchDto taskPatchDTO) throws Exception;
}
