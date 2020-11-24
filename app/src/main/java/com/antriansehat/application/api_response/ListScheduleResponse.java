package com.antriansehat.application.api_response;

import com.antriansehat.application.model.ScheduleOfHA;

import java.util.List;

public class ListScheduleResponse {
    public boolean success;
    public String message;
    public List<ScheduleOfHA> data;
}
