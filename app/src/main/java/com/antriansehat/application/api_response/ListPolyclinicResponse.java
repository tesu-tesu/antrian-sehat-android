package com.antriansehat.application.api_response;

import com.antriansehat.application.model.Pagination;
import com.antriansehat.application.model.Polyclinic;

public class ListPolyclinicResponse {
    public boolean success;
    public String message;
    public Pagination<Polyclinic> data;
}
