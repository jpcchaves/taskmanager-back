package com.ws.taskmanager.data.DTO;

public class UpdateUserRequestDto {
    private String name;
    private String currentPassword;
    private String password;

    public UpdateUserRequestDto() {
    }

    public UpdateUserRequestDto(String name, String currentPassword, String password) {
        this.name = name;
        this.currentPassword = currentPassword;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
