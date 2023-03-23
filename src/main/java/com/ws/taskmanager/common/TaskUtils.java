package com.ws.taskmanager.common;

import com.ws.taskmanager.data.DTO.TaskDto;
import com.ws.taskmanager.data.DTO.TaskResponseDto;
import com.ws.taskmanager.data.DTO.TasksResponseDtoPaginated;
import com.ws.taskmanager.models.TaskModel;
import org.springframework.data.domain.Page;

import java.util.List;

public class TaskUtils {

    public static TasksResponseDtoPaginated buildTaskResponseDtoPaginated(List<TaskResponseDto> taskResponseDtoList, Page<TaskModel> tasksPage) {
        TasksResponseDtoPaginated taskResponseDto = new TasksResponseDtoPaginated();
        taskResponseDto.setContent(taskResponseDtoList);
        taskResponseDto.setPageNo(tasksPage.getNumber());
        taskResponseDto.setPageSize(tasksPage.getSize());
        taskResponseDto.setTotalElements(tasksPage.getTotalElements());
        taskResponseDto.setTotalPages(tasksPage.getTotalPages());
        taskResponseDto.setLast(tasksPage.isLast());
        return taskResponseDto;
    }

    public static TaskModel copyPropertiesFromTaskModelToTaskDto(TaskModel entity, TaskDto taskDTO) {
        entity.setTask(taskDTO.getTask());
        entity.setConcluded(taskDTO.getConcluded());
        entity.setDeadline(taskDTO.getDeadline());
        return entity;
    }
}
