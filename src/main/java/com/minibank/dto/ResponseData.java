package com.minibank.dto;

import java.util.ArrayList;
import java.util.List;

public class ResponseData<T> {

    private int statusCode;
    private List<String> messages = new ArrayList<>();
    private T payload;

    public ResponseData() {
    }

    public ResponseData(int statusCode, List<String> messages, T payload) {
        this.statusCode = statusCode;
        this.messages = messages;
        this.payload = payload;
    }

    public ResponseData(int statusCode, List<String> messages) {
        this.statusCode = statusCode;
        this.messages = messages;
    }

    public int getStatusCode() {
        return statusCode;
    }


    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<String> getMessages() {
        return messages;
    }
    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
    public T getPayload() {
        return payload;
    }
    public void setPayload(T payload) {
        this.payload = payload;
    }



}