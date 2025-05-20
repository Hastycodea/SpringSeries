package com.example.recapcrud.controller;

import com.example.recapcrud.dtos.UserDto;
import com.example.recapcrud.model.User;
import com.example.recapcrud.repo.UserRepository;
import com.example.recapcrud.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

//    @GetMapping
//    public List<UserDto> getAllUsers(@RequestParam (required = false, defaultValue = "", name = "sort") String sort) {
//        if (!Set.of("userName").contains(sort)) {
//            sort = "userName";
//        }
//        return userRepository.findAll()
//                .stream()
//                .map(user -> new UserDto(user.getId(), user.getUsername()))
//                .toList();
//    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<UserDto> getUserById(@PathVariable int id) {
//        User user = userRepository.findById(id).orElse(null);
//
//        if(user == null) {
//            return ResponseEntity.notFound().build();
//        } else {
//            UserDto userDto = new UserDto(user.getId(), user.getUsername());
//            return ResponseEntity.ok(userDto);
//        }
//    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int id) {
       return ResponseEntity.ok(userService.getUserById(id));
    }

}
