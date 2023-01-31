package com.ws.taskmanager.controller;


import com.ws.taskmanager.data.DTO.TaskDTO;
import com.ws.taskmanager.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> listTaskById(@PathVariable UUID id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.listTaskById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable UUID id, @RequestBody TaskDTO taskDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTask(id, taskDTO));
    }


}
