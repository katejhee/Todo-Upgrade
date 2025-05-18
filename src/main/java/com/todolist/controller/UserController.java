package com.todolist.controller;

import com.todolist.entity.User;
import com.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.todolist.entity.Todo;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        return ResponseEntity.status(201).body(userService.createUser(username));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @PostMapping("/{followerId}/follow/{followeeId}")
    public ResponseEntity<Void> followUser(@PathVariable Long followerId, @PathVariable Long followeeId) {
        userService.followUser(followerId, followeeId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{followerId}/unfollow/{followeeId}")
    public ResponseEntity<Void> unfollowUser(@PathVariable Long followerId, @PathVariable Long followeeId) {
        userService.unfollowUser(followerId, followeeId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/following")
    public ResponseEntity<List<User>> getFollowing(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getFollowing(id));
    }

    @GetMapping("/{id}/followers")
    public ResponseEntity<List<User>> getFollowers(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getFollowers(id));
    }

    @GetMapping("/{id}/todos")
    public ResponseEntity<List<Todo>> getUserTodos(@PathVariable Long id, @RequestParam(required = false, defaultValue = "false") boolean increaseView) {
        return ResponseEntity.ok(userService.getUserTodos(id, increaseView));
    }

    @GetMapping("/{id}/my-todos")
    public ResponseEntity<List<Todo>> getMyTodos(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getMyTodos(id));
    }
}
