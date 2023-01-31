package com.ws.taskmanager.services;

import com.ws.taskmanager.data.DTO.TaskDTO;
import com.ws.taskmanager.mapper.ModelMapperService;
import com.ws.taskmanager.models.TaskModel;
import com.ws.taskmanager.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public TaskDTO createTask(TaskDTO taskDTO) {

        taskDTO.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));

        var entity = ModelMapperService.parseObject(taskDTO, TaskModel.class);

        var dto = ModelMapperService.parseObject(taskRepository.save(entity), TaskDTO.class);

        return dto;
    }

    public List<TaskDTO> listAllTasks() {

        var tasks = ModelMapperService.parseListObjects(taskRepository.findAll(), TaskDTO.class);

        return tasks;

    }

    public TaskDTO listTaskById(UUID id) throws Exception {

        var task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("not found!"));

        return ModelMapperService.parseObject(task, TaskDTO.class);

    }


}
