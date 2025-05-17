package com.todolist.controller;

import com.todolist.dto.TodoCreateRequest;
import com.todolist.entity.Todo;
import com.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody TodoCreateRequest request) {
        System.out.println("title = " + request.getTitle());
        System.out.println("subTasks = " + request.getSubTasks());
        Todo created = todoService.createTodo(request);
        return ResponseEntity.status(201).body(created);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Todo> updateTodoStatus(@PathVariable Long id, @RequestBody Map<String, String> request) {
        String status = request.get("status");
        Todo updated = todoService.updateStatus(id, status);
        return ResponseEntity.ok(updated);
    }

    @GetMapping
    public ResponseEntity<Page<Todo>> getTodos(Pageable pageable) {
        Page<Todo> todos = todoService.findAllTodos(pageable);
        return ResponseEntity.ok(todos);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTodos(@RequestBody List<Long> ids) {
        todoService.deleteTodos(ids);
        return ResponseEntity.noContent().build();
    }
}
