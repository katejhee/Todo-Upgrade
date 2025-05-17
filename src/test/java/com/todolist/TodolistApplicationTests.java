package com.todolist;

import com.todolist.dto.TodoCreateRequest;
import com.todolist.dto.SubTaskRequest;
import com.todolist.entity.Todo;
import com.todolist.service.TodoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TodolistApplicationTests {

    @Autowired
    private TodoService todoService;

    @Test
    @DisplayName("TODO를 생성하고 ID를 반환한다")
    void createTodo() {
        TodoCreateRequest request = new TodoCreateRequest("테스트", List.of(
            new SubTaskRequest("세부1"),
            new SubTaskRequest("세부2")
        ));
        Todo saved = todoService.createTodo(request);
        assertNotNull(saved.getId());
        assertEquals("테스트", saved.getTitle());
    }

    @Test
    @DisplayName("TODO 목록을 페이지로 조회한다")
    void getTodos() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Todo> todos = todoService.findAllTodos(pageable);
        assertNotNull(todos);
    }

    @Test
    @DisplayName("TODO 상태를 변경한다")
    void updateTodoStatus() {
        TodoCreateRequest request = new TodoCreateRequest("상태변경 테스트", List.of(
            new SubTaskRequest("세부1")
        ));
        Todo saved = todoService.createTodo(request);

        Todo updated = todoService.updateStatus(saved.getId(), "DONE");
        assertEquals("DONE", updated.getStatus().name());
    }

    @Test
    @DisplayName("TODO를 삭제한다")
    void deleteTodo() {
        TodoCreateRequest request = new TodoCreateRequest("삭제 테스트", List.of(
            new SubTaskRequest("세부1")
        ));
        Todo saved = todoService.createTodo(request);

        todoService.deleteTodos(List.of(saved.getId()));
        Page<Todo> todos = todoService.findAllTodos(PageRequest.of(0, 10));
        assertFalse(todos.getContent().stream().anyMatch(t -> t.getId().equals(saved.getId())));
    }
}
