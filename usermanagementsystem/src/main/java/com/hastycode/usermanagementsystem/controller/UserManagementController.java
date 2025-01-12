package com.hastycode.usermanagementsystem.controller;

import com.hastycode.usermanagementsystem.dto.ReqRes;
import com.hastycode.usermanagementsystem.entity.OurUsers;
import com.hastycode.usermanagementsystem.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserManagementController {

    @Autowired
    private UserManagementService userManagementService;

    @GetMapping("/auth/home")
    public String home() {
        return "Welcome home!";
    }

    @PostMapping("/auth/register")
    public ResponseEntity<ReqRes> register(@RequestBody ReqRes res) {
        return ResponseEntity.ok(userManagementService.register(res));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes res) {
        return ResponseEntity.ok(userManagementService.login(res));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqRes> refresh(@RequestBody ReqRes res) {
        return ResponseEntity.ok(userManagementService.refreshToken(res));
    }

    @GetMapping("/admin/get-all-users")
    public ResponseEntity<ReqRes> getAllUsers() {
       return ResponseEntity.ok(userManagementService.getAllUsers());
    }

    @GetMapping("/admin/get-users/{userId}")
    public ResponseEntity<ReqRes> getUserById(@PathVariable long userId) {
        return ResponseEntity.ok(userManagementService.getUserById(userId));
    }

    @PutMapping("/admin/update/{userId}")
    public ResponseEntity<ReqRes> updateUser(@PathVariable long userId, @RequestBody OurUsers ourUsers){
        return ResponseEntity.ok(userManagementService.updateUser(userId, ourUsers));
    }

    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<ReqRes> getMyProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        ReqRes res = userManagementService.getMyInfo(email);
        return ResponseEntity.status(res.getStatusCode()).body(res);
    }

    @DeleteMapping("/admin/delete/{userId}")
    public ResponseEntity<ReqRes> deleteUser(@PathVariable long userId){
        return ResponseEntity.ok(userManagementService.deleteUser(userId));
    }

}
