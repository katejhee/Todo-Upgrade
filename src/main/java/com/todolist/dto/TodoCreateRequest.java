package com.todolist.dto;

import java.util.List;

public class TodoCreateRequest {
    private String title;
    private List<SubTaskRequest> subTasks;

    public TodoCreateRequest() {
    }

    public TodoCreateRequest(String title, List<SubTaskRequest> subTasks) {
        this.title = title;
        this.subTasks = subTasks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubTaskRequest> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTaskRequest> subTasks) {
        this.subTasks = subTasks;
    }
}
