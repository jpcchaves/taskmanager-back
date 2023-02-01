package com.ws.taskmanager.services;

import com.ws.taskmanager.controller.TaskController;
import com.ws.taskmanager.data.DTO.TaskDTO;
import com.ws.taskmanager.data.DTO.TaskResponseDTO;
import com.ws.taskmanager.exceptions.ResourceNotFoundException;
import com.ws.taskmanager.mapper.DozerMapper;
import com.ws.taskmanager.models.TaskModel;
import com.ws.taskmanager.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


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
    public TaskResponseDTO createTask(TaskDTO taskDTO) throws Exception {

        var task = DozerMapper.parseObject(taskDTO, TaskModel.class);
        task.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));

        var dto = DozerMapper.parseObject(taskRepository.save(task), TaskResponseDTO.class);
        dto.add(linkTo(methodOn(TaskController.class).listTaskById(dto.getKey())).withSelfRel());

        return dto;
    }

    public List<TaskResponseDTO> listAllTasks() {

        var entities = DozerMapper.parseListObjects(taskRepository.findAll(), TaskModel.class);
        var tasks = DozerMapper.parseListObjects(entities, TaskResponseDTO.class);

        tasks
            .forEach((task) -> {
                try {
                    task.add(linkTo(methodOn(TaskController.class).listTaskById(task.getKey())).withSelfRel());
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            });

        return tasks;

    }

    public TaskResponseDTO listTaskById(UUID id) throws Exception {

        var entity = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado uma task com o ID informado!"));

        var task = DozerMapper.parseObject(entity, TaskModel.class);

        var dto = DozerMapper.parseObject(task, TaskResponseDTO.class);


        dto.add(linkTo(methodOn(TaskController.class).listTaskById(id)).withSelfRel());

        return dto;
    }

    @Transactional
    public TaskResponseDTO updateTask(UUID id, TaskDTO taskDTO) throws Exception {

        var entity = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado uma task com o ID informado!"));

        entity.setTask(taskDTO.getTask());
        entity.setConcluded(taskDTO.getConcluded());
        entity.setDeadline(taskDTO.getDeadline());

        var task = DozerMapper.parseObject(taskRepository.save(entity), TaskModel.class);

        var dto = DozerMapper.parseObject(task, TaskResponseDTO.class);
        dto.add(linkTo(methodOn(TaskController.class).listTaskById(dto.getKey())).withSelfRel());

        return dto;
    }

    @Transactional
    public void deleteTask(UUID id) {
        var task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não é possível deletar essa task pois ela não existe!"));
        taskRepository.deleteById(id);
    }


}
