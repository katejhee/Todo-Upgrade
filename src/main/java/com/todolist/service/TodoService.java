package com.todolist.service;

import com.todolist.dto.TodoCreateRequest;
import com.todolist.dto.SubTaskRequest;
import com.todolist.entity.SubTask;
import com.todolist.entity.Todo;
import com.todolist.entity.TodoStatus;
import com.todolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public Todo createTodo(TodoCreateRequest request) {
        Todo todo = new Todo();
        todo.setTitle(request.getTitle());

        if (request.getSubTasks() != null) {
            if (request.getSubTasks().size() > 3) {
                throw new IllegalArgumentException("세부 할 일은 최대 3개까지 등록할 수 있습니다.");
            }
            for (SubTaskRequest subTaskRequest : request.getSubTasks()) {
                SubTask subTask = new SubTask();
                subTask.setContent(subTaskRequest.getContent());
                todo.addSubTask(subTask); // this sets both sides via addSubTask method
            }
        }

        return todoRepository.save(todo);
    }

    public Todo updateStatus(Long id, String status) {
        Todo todo = todoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 TODO가 존재하지 않습니다."));

        try {
            todo.setStatus(Enum.valueOf(TodoStatus.class, status.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("유효하지 않은 상태입니다. (PENDING, IN_PROGRESS, DONE 중 하나여야 합니다)");
        }

        return todoRepository.save(todo);
    }

    public Page<Todo> findAllTodos(Pageable pageable) {
        return todoRepository.findAll(pageable);
    }

    public void deleteTodos(java.util.List<Long> ids) {
        java.util.List<Todo> todos = todoRepository.findAllById(ids);
        if (todos.isEmpty()) {
            throw new IllegalArgumentException("삭제할 TODO가 존재하지 않습니다.");
        }
        todoRepository.deleteAll(todos);
    }
}
