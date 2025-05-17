package com.todolist.dto;

public class SubTaskRequest {

    private String content;

    // 기본 생성자 (필수!)
    public SubTaskRequest() {
    }

    public SubTaskRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}