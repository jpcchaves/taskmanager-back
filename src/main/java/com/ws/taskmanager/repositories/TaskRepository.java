package com.ws.taskmanager.repositories;

import com.ws.taskmanager.models.TaskModel;
import com.ws.taskmanager.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, UUID> {
    Page<TaskModel> findByUser(UserModel userModel, Pageable pageable);

    List<TaskModel> findAllByUser(UserModel userModel);

    TaskModel findByUserAndId(UserModel user, UUID id);

    List<TaskModel> findAllByUserAndConcluded(UserModel user, Boolean concluded);
}
