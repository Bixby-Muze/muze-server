package com.muze.util;

import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
public class ResponseMessage {

    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_KEY = "result";
    private int code;
    private boolean status;
    private String message;
    private Date timestamp;
    private Map<String, Object> data;

    public ResponseMessage() {
        this(HttpStatus.OK);
    }

    public ResponseMessage(HttpStatus httpStatus) {
        this.data = new HashMap<>();
        this.code = httpStatus.value();
        this.status = (httpStatus.isError()) ? false : true;
        this.message = httpStatus.getReasonPhrase();
        this.timestamp = new Date();
    }

    public ResponseMessage(HttpStatus status, Object result) {
        this(status);
        this.data.put(DEFAULT_KEY, result);
    }

    public void add(String key, Object result) {
        this.data.put(key, result);
    }

    public void remove(String key) {
        this.data.remove(key);
    }
}