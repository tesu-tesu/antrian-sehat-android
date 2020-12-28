package com.antriansehat.application.api_response;

import com.antriansehat.application.model.User;

public class LoginResponse {
    public boolean success;
    public String access_token;
    public String message;
    public User user; //just for validation role when login
}
