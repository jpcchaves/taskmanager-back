package com.ws.taskmanager.services.impl;

import com.ws.taskmanager.models.UserModel;
import com.ws.taskmanager.repositories.UserRepository;
import com.ws.taskmanager.services.SecurityContextService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SecurityContextServiceImpl implements SecurityContextService {

    private final UserRepository userRepository;

    public SecurityContextServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserModel getCurrentLoggedUser() {
        var securityContextUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var user = userRepository.findByUsernameOrEmail(securityContextUser.getUsername(), securityContextUser.getUsername());
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("Ocorreu um erro inesperado.");
        }
    }
}
