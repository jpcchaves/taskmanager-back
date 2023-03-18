package com.ws.taskmanager.data.DTO;


public class JWTAuthResponseDto {
    private String accessToken;
    private String tokenType = "Bearer";
    private UserDto user;

    public JWTAuthResponseDto() {
    }

    public JWTAuthResponseDto(String accessToken, String tokenType, UserDto user) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
