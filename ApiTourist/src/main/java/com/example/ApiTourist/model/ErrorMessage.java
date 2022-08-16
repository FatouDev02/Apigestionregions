package com.example.ApiTourist.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ErrorMessage {
    public static ResponseEntity<Object> ErreurReponse(String message, HttpStatus status, Object object){

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("Message",message);
        map.put("status", status.value());
        map.put("data",object);

        return new ResponseEntity<Object>(map,status);
    }
}
