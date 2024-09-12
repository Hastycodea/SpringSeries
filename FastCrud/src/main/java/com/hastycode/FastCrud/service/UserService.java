package com.hastycode.FastCrud.service;

import com.hastycode.FastCrud.model.User;
import com.hastycode.FastCrud.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepo repo;

    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    public List<User> allUsers() {
        return repo.findAll();
    }

    public User getUserById(int id) {
        return repo.findById(id).orElse(null);
    }

    public User addUser(User user) {
        return repo.save(user);
    }

    public User updateUser(User user) {
        return repo.save(user);
    }

    public void deleteUser(int id) {
        repo.deleteById(id);
    }
}
