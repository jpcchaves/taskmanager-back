package com.ws.taskmanager.services;

import com.ws.taskmanager.models.UserModel;

public interface SecurityContextService {
    UserModel getCurrentLoggedUser();
}
