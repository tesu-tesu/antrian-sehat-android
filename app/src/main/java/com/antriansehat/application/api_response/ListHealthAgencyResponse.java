package com.antriansehat.application.api_response;

import com.antriansehat.application.model.HealthAgency;

import java.util.List;

public class ListHealthAgencyResponse {
    public boolean success;
    public String message;
    public List<HealthAgency> data;
}
