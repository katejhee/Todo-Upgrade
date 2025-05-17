package com.todolist.entity;

import jakarta.persistence.*;

@Entity
public class SubTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Enumerated(EnumType.STRING)
    private SubTaskStatus status = SubTaskStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "todo_id")
    private Todo todo;
    public SubTask() {}
    public void setContent(String content) {
        this.content = content;
    }

    public void setTodo(Todo todo) {
        this.todo = todo;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public SubTaskStatus getStatus() {
        return status;
    }

    public Todo getTodo() {
        return todo;
    }
}
