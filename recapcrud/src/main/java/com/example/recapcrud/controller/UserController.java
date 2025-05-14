package com.example.recapcrud.controller;

import com.example.recapcrud.dtos.UserDto;
import com.example.recapcrud.model.User;
import com.example.recapcrud.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public List<UserDto> getAllUsers(@RequestParam (required = false, defaultValue = "", name = "sort") String sort) {
        if (!Set.of("userName").contains(sort)) {
            sort = "userName";
        }
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDto(user.getId(), user.getUsername()))
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int id) {
        User user = userRepository.findById(id).orElse(null);

        if(user == null) {
            return ResponseEntity.notFound().build();
        } else {
            UserDto userDto = new UserDto(user.getId(), user.getUsername());
            return ResponseEntity.ok(userDto);
        }
    }

}
