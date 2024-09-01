package com.hms.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ApiResponse {
    private int status;
    private String message;
    private String path;
    private String code;
    private Map<String, Object> data = new HashMap<>();
    private Map<String, String> errorList;

    public static ApiResponse responseBuilder(int status, String code, String path, String key, Object data) {
        ApiResponse response = new ApiResponse();
        response.setStatus(status);
        response.setPath(path);
        response.setMessage(APP_MSG.MESSAGE.get(code));
        response.setCode(code);
        response.putData(key, data);
        return response;
    }

    public void putData(String key, Object Value) {
        data.put(key, Value);
    }
}
