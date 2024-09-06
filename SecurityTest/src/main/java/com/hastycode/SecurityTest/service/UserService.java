package com.hastycode.SecurityTest.service;

import com.hastycode.SecurityTest.model.Users;
import com.hastycode.SecurityTest.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepo repo;

    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    public List<Users> getUsers() {
        return repo.findAll();
    }

    public Users addUser(Users user) {
        return repo.save(user);
    }
}
