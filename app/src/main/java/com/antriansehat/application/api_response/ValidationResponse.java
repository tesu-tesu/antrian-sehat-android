package com.antriansehat.application.api_response;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;
import java.util.ArrayList;


public class ValidationResponse {
    String error;
    String[] email;
    String[] password;
    @SerializedName("confirm_password")
    String[] confirmPassword;
    String[] phone;
    String[] name;
    @SerializedName("residence_number")
    String[] residenceNumber;

    public ValidationResponse(String error, String[] email, String[] password, String[] confirmPassword, String[] phone, String[] name, String[] residenceNumber) {
        this.error = error;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.phone = phone;
        this.name = name;
        this.residenceNumber = residenceNumber;
    }

    public String getError() {
        return error;
    }

    public String[] getEmail() {
        return email;
    }

    public String[] getPassword() {
        return password;
    }

    public String[] getConfirmPassword() {
        return confirmPassword;
    }

    public String[] getPhone() {
        return phone;
    }

    public String[] getName() {
        return name;
    }

    public String[] getResidenceNumber() {
        return residenceNumber;
    }

    public String getData() throws IllegalAccessException {
        StringBuffer stringBuffer = new StringBuffer();

        for (Field f : getClass().getDeclaredFields()) {
            if (f.get(this) != null) {
                stringBuffer.append("\n");
                if(f.getName().equals("error")) {
                    stringBuffer.append(f.get(this));
                    continue;
                }
                String[] data = (String[]) f.get(this);
                stringBuffer.append(getAllString(data));
            }
        }
        return stringBuffer.toString().trim();
    }

    public String getAllString(String[] data) {
        StringBuffer stringBuffer = new StringBuffer();
        for(String x : data) {
            stringBuffer.append(x + "\n");
        }
        return stringBuffer.toString();
    }
}
