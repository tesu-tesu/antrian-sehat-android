package com.antriansehat.application.api_response;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;
import java.util.ArrayList;


public class ValidationResponse {
    String[] email;
    String[] password;
    @SerializedName("confirm_password")
    String[] confirmPassword;
    String[] name;

    public ValidationResponse(String[] email, String[] password, String[] confirmPassword, String[] name) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.name = name;
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

    public String[] getName() {
        return name;
    }

    public String getData() throws IllegalAccessException {
        StringBuffer stringBuffer = new StringBuffer();

        for (Field f : getClass().getDeclaredFields()) {
            if (f.get(this) != null) {
                stringBuffer.append("\n");
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
