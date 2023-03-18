package com.ws.taskmanager.repositories;

import com.ws.taskmanager.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleModel, Long> {
    Optional<RoleModel> findByName(String name);
}
