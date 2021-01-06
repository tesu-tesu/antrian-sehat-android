package com.antriansehat.application.api_response;

public class BaseResponse<T> {
    public boolean success;
    public String message;
    public T data;
}
