package com.todolist.service;

import com.todolist.entity.Todo;

import com.todolist.entity.User;
import com.todolist.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }

    @Transactional
    public void followUser(Long followerId, Long followeeId) {
        if (followerId.equals(followeeId)) {
            throw new IllegalArgumentException("You cannot follow yourself.");
        }

        User follower = findUserById(followerId);
        User followee = findUserById(followeeId);

        if (!follower.getFollowing().contains(followee)) {
            follower.getFollowing().add(followee);
            userRepository.save(follower);
        }
    }

    @Transactional
    public void unfollowUser(Long followerId, Long followeeId) {
        User follower = findUserById(followerId);
        User followee = findUserById(followeeId);

        if (follower.getFollowing().contains(followee)) {
            follower.getFollowing().remove(followee);
            userRepository.save(follower);
        }
    }

    public List<User> getFollowers(Long userId) {
        return findUserById(userId).getFollowers();
    }

    public List<User> getFollowing(Long userId) {
        return findUserById(userId).getFollowing();
    }

    public boolean isFollowing(Long followerId, Long followeeId) {
        User follower = findUserById(followerId);
        User followee = findUserById(followeeId);
        return follower.getFollowing().contains(followee);
    }
    public boolean isMutualFollow(Long userId1, Long userId2) {
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);
        return user1.getFollowing().contains(user2) && user2.getFollowing().contains(user1);
    }
    public User createUser(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username must not be empty.");
        }

        User user = new User();
        user.setUsername(username);
        return userRepository.save(user);
    }
    public List<Todo> getUserTodos(Long id, boolean increaseView) {
        User user = findUserById(id);
        List<Todo> todos = user.getTodos();

        if (increaseView) {
            for (Todo todo : todos) {
                todo.increaseViewCount();
                if (todo.getSubTasks() != null) {
                    todo.getSubTasks().size();
                }
            }
        }

        return todos;
    }
    public List<Todo> getMyTodos(Long myId) {
        return getUserTodos(myId, false);
    }
}