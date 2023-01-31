package com.ws.taskmanager.controller;


import com.ws.taskmanager.data.DTO.TaskDTO;
import com.ws.taskmanager.models.TaskModel;
import com.ws.taskmanager.services.TaskService;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/task")
public class TaskController {

  final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @PostMapping("/new")
  public ResponseEntity<TaskDTO> createTask(@RequestBody @Valid TaskDTO taskDTO) {
    return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(taskDTO));
  }

  @GetMapping
  public ResponseEntity<List<TaskDTO>> listAllTasks() {
    return ResponseEntity.status(HttpStatus.OK).body(taskService.listAllTasks());
  }


}
