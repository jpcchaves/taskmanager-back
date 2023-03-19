package com.ws.taskmanager.services.impl;

import com.ws.taskmanager.controller.TaskController;
import com.ws.taskmanager.data.DTO.*;
import com.ws.taskmanager.exceptions.BadRequestException;
import com.ws.taskmanager.exceptions.ResourceNotFoundException;
import com.ws.taskmanager.mapper.DozerMapper;
import com.ws.taskmanager.models.TaskModel;
import com.ws.taskmanager.repositories.TaskRepository;
import com.ws.taskmanager.repositories.UserRepository;
import com.ws.taskmanager.services.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public TaskResponseDto createTask(TaskCreateDto taskDTO) throws Exception {

        var task = DozerMapper.parseObject(taskDTO, TaskModel.class);
        task.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        task.setConcluded(false);

        var securityContextUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = userRepository.findByUsernameOrEmail(securityContextUser.getUsername(), securityContextUser.getUsername());

        task.setUserModel(user.get());

        var dto = DozerMapper.parseObject(taskRepository.save(task), TaskResponseDto.class);
        dto.add(linkTo(methodOn(TaskController.class).listTaskById(dto.getKey())).withSelfRel());

        return dto;
    }

    @Override
    public TasksResponseDtoPaginated findByUserWithPagination(Pageable pageable) {

        var securityContextUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = userRepository.findByUsernameOrEmail(securityContextUser.getUsername(), securityContextUser.getUsername());

        var tasksPage = taskRepository.findByUser(user.get(), pageable);

        var tasksDto = tasksPage.getContent().stream().map(task ->
                DozerMapper.parseObject(task, TaskResponseDto.class)
        ).toList();

        var tasksDtoHateoas = tasksDto.stream().map(task -> {
            try {
                return task.add(linkTo(methodOn(TaskController.class).listTaskById(task.getKey())).withSelfRel());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());

        TasksResponseDtoPaginated taskResponseDto = new TasksResponseDtoPaginated();
        taskResponseDto.setContent(tasksDtoHateoas);
        taskResponseDto.setPageNo(tasksPage.getNumber());
        taskResponseDto.setPageSize(tasksPage.getSize());
        taskResponseDto.setTotalElements(tasksPage.getTotalElements());
        taskResponseDto.setTotalPages(tasksPage.getTotalPages());
        taskResponseDto.setLast(tasksPage.isLast());

        return taskResponseDto;
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
