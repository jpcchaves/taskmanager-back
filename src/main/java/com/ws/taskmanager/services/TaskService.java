package com.ws.taskmanager.services;

import com.ws.taskmanager.data.DTO.TaskDTO;
import com.ws.taskmanager.exceptions.ResourceNotFoundException;
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

    public TaskDTO listTaskById(UUID id) {

        var task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado uma task com o ID informado!"));

        return ModelMapperService.parseObject(task, TaskDTO.class);

    }

    @Transactional
    public TaskDTO updateTask(UUID id, TaskDTO taskDTO) {

        var task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado uma task com o ID informado!"));

        task.setTask(taskDTO.getTask());
        task.setConcluded(taskDTO.getConcluded());
        task.setDeadline(taskDTO.getDeadline());

        var dto = ModelMapperService.parseObject(taskRepository.save(task), TaskDTO.class);

        return dto;
    }

    public void deleteTask(UUID id) {
        var task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não é possível deletar essa task pois ela não existe!"));
        taskRepository.deleteById(id);
    }


}
