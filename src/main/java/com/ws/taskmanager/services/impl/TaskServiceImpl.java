package com.ws.taskmanager.services.impl;

import com.ws.taskmanager.common.MapperUtils;
import com.ws.taskmanager.common.TaskUtils;
import com.ws.taskmanager.controller.TaskController;
import com.ws.taskmanager.data.DTO.*;
import com.ws.taskmanager.exceptions.BadRequestException;
import com.ws.taskmanager.exceptions.ResourceNotFoundException;
import com.ws.taskmanager.mapper.DozerMapper;
import com.ws.taskmanager.models.TaskModel;
import com.ws.taskmanager.repositories.TaskRepository;
import com.ws.taskmanager.services.SecurityContextService;
import com.ws.taskmanager.services.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
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
    private final SecurityContextService securityContextService;
    private final MapperUtils mapperUtils;

    public TaskServiceImpl(TaskRepository taskRepository,
                           SecurityContextService securityContextService,
                            MapperUtils mapperUtils) {
        this.taskRepository = taskRepository;
        this.securityContextService = securityContextService;
        this.mapperUtils = mapperUtils;
    }

    @Transactional
    public TaskResponseDto createTask(TaskCreateDto taskDTO) throws Exception {

        var task = DozerMapper.parseObject(taskDTO, TaskModel.class);
        task.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        task.setConcluded(false);

        var user = securityContextService.getCurrentLoggedUser();

        task.setUserModel(user);

        //        var dto = DozerMapper.parseObject(taskRepository.save(task), TaskResponseDto.class);
//        dto.add(linkTo(methodOn(TaskController.class).listTaskById(dto.getKey())).withSelfRel());

        return mapperUtils
                .parseObject(taskRepository.save(task), TaskResponseDto.class);
    }

    @Override
    public TasksResponseDtoPaginated findByUserWithPagination(Pageable pageable) {

        var user = securityContextService.getCurrentLoggedUser();
        taskRepository.findByUser(user, pageable);

        var taskPage = taskRepository.findByUser(user, pageable);
        var tasksDto = mapperUtils.parseListObjects(taskPage.getContent(), TaskResponseDto.class);

        return TaskUtils.buildTaskResponseDtoPaginated(tasksDto, taskPage);
    }

    public TaskResponseDto listTaskById(UUID id) throws Exception {
        var user = securityContextService.getCurrentLoggedUser();

        var entity = taskRepository.findByUserAndId(user, id);

        if (entity == null) {
            throw new ResourceNotFoundException("Não foi encontrado uma task com o ID informado!");
        }

        var task = mapperUtils.parseObject(entity, TaskModel.class);

        return mapperUtils.parseObject(task, TaskResponseDto.class);
    }

    @Transactional
    public TaskResponseDto updateTask(UUID id, TaskDto taskDTO) throws Exception {
        var user = securityContextService.getCurrentLoggedUser();


        var entity = taskRepository.findByUserAndId(user, id);

        if (entity == null) {
            throw new ResourceNotFoundException("Não foi encontrado uma task com o ID informado!");
        }

        var updatedTask = TaskUtils.copyPropertiesFromTaskModelToTaskDto(entity, taskDTO);

        var task = mapperUtils.parseObject(taskRepository.save(updatedTask), TaskModel.class);

        return mapperUtils.parseObject(task, TaskResponseDto.class);
    }

    @Transactional
    public void deleteTask(UUID id) {
        var user = securityContextService.getCurrentLoggedUser();
        var task = taskRepository.findByUserAndId(user, id);

        if (task == null) {
            throw new ResourceNotFoundException("Não é possível deletar essa task pois ela não existe!");
        }

        taskRepository.deleteById(id);
    }

    @Transactional
    public TaskPatchDto updateTaskSituation(UUID id, TaskPatchDto taskPatchDTO) throws Exception {
        var user = securityContextService.getCurrentLoggedUser();
        var entity = taskRepository.findByUserAndId(user, id);

        if (entity == null) {
            throw new ResourceNotFoundException("Não é possível deletar essa task pois ela não existe!");
        }

        if (entity.getDeadline().isBefore(LocalDateTime.now(ZoneId.of("UTC")))) {
            throw new BadRequestException("Não é possível atualizar a situaçao da tarefa porque seu prazo já está expirado!");
        }

        entity.setConcluded(taskPatchDTO.getConcluded());

        var task = mapperUtils.parseObject(taskRepository.save(entity), TaskModel.class);

        return mapperUtils.parseObject(task, TaskPatchDto.class);
    }

    @Override
    public Integer countByUserAndConcluded(Boolean concluded) {
        var user = securityContextService.getCurrentLoggedUser();

        return taskRepository.countByUserAndConcluded(user, concluded);
    }
}
