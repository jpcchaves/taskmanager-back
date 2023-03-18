package com.ws.taskmanager.services;

import com.ws.taskmanager.controller.TaskController;
import com.ws.taskmanager.data.DTO.TaskCreateDto;
import com.ws.taskmanager.data.DTO.TaskDto;
import com.ws.taskmanager.data.DTO.TaskPatchDto;
import com.ws.taskmanager.data.DTO.TaskResponseDto;
import com.ws.taskmanager.exceptions.BadRequestException;
import com.ws.taskmanager.exceptions.ResourceNotFoundException;
import com.ws.taskmanager.mapper.DozerMapper;
import com.ws.taskmanager.models.TaskModel;
import com.ws.taskmanager.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class TaskService {

    final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public TaskResponseDto createTask(TaskCreateDto taskDTO) throws Exception {
        var task = DozerMapper.parseObject(taskDTO, TaskModel.class);
        task.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        task.setConcluded(false);

        var dto = DozerMapper.parseObject(taskRepository.save(task), TaskResponseDto.class);
        dto.add(linkTo(methodOn(TaskController.class).listTaskById(dto.getKey())).withSelfRel());

        return dto;
    }

    public Page<TaskResponseDto> listAllTasks(Pageable pageable) {

        var tasksPage = taskRepository.findAll(pageable);
        var tasksPageDTO = tasksPage.map(entity -> DozerMapper.parseObject(entity, TaskResponseDto.class));

        tasksPageDTO.map(task -> {
            try {
                return task.add(linkTo(methodOn(TaskController.class).listTaskById(task.getKey())).withSelfRel());
            } catch (Exception e) {
                throw new ResourceNotFoundException("Ocorreu um erro na listagem de tasks!");
            }
        });

        return tasksPageDTO;
    }

    public TaskResponseDto listTaskById(UUID id) throws Exception {

        var entity = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado uma task com o ID informado!"));

        var task = DozerMapper.parseObject(entity, TaskModel.class);

        var dto = DozerMapper.parseObject(task, TaskResponseDto.class);


        dto.add(linkTo(methodOn(TaskController.class).listTaskById(id)).withSelfRel());

        return dto;
    }

    @Transactional
    public TaskResponseDto updateTask(UUID id, TaskDto taskDTO) throws Exception {
        var entity = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado uma task com o ID informado!"));

        entity.setTask(taskDTO.getTask());
        entity.setConcluded(taskDTO.getConcluded());
        entity.setDeadline(taskDTO.getDeadline());

        var task = DozerMapper.parseObject(taskRepository.save(entity), TaskModel.class);

        var dto = DozerMapper.parseObject(task, TaskResponseDto.class);
        dto.add(linkTo(methodOn(TaskController.class).listTaskById(dto.getKey())).withSelfRel());

        return dto;
    }

    @Transactional
    public void deleteTask(UUID id) {
        var entity = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não é possível deletar essa task pois ela não existe!"));
        taskRepository.deleteById(id);
    }

    @Transactional
    public TaskPatchDto updateTaskSituation(UUID id, TaskPatchDto taskPatchDTO) throws Exception {

        var entity = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não é possível deletar essa task pois ela não existe!"));

        if (entity.getDeadline().isBefore(LocalDateTime.now(ZoneId.of("UTC")))) {
            throw new BadRequestException("Não é possível atualizar a situaçao da tarefa porque seu prazo já está expirado!");
        }

        entity.setConcluded(taskPatchDTO.getConcluded());

        var task = DozerMapper.parseObject(taskRepository.save(entity), TaskModel.class);

        var dto = DozerMapper.parseObject(task, TaskPatchDto.class);
        dto.add(linkTo(methodOn(TaskController.class).listTaskById(dto.getKey())).withSelfRel());

        return dto;
    }
}
