package com.ws.taskmanager.services.impl;

import com.ws.taskmanager.models.UserModel;
import com.ws.taskmanager.repositories.UserRepository;
import com.ws.taskmanager.services.SecurityContextService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SecurityContextServiceImpl implements SecurityContextService {

    @Override
    public UserModel getCurrentLoggedUser() {
        return (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
