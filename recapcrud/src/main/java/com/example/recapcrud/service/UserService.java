package com.example.recapcrud.service;

import com.example.recapcrud.dtos.UserDto;
import com.example.recapcrud.model.User;
import com.example.recapcrud.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<UserDto> getAllUsers() {
       return repo.findAll()
               .stream()
               .map(user -> new UserDto(user.getId(), user.getUsername()))
               .toList();
    }

    public UserDto getUserById(int id) {
        User user = repo.findById(id).orElse(null);

        if(user == null) {
            return null;
        }

        UserDto userDto = new UserDto(user.getId(), user.getUsername());
        return userDto;
    }


}