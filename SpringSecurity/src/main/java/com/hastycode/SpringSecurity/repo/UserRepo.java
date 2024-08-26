package com.hastycode.SpringSecurity.repo;

import com.hastycode.SpringSecurity.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
    Users findUserByUsername(String username);
}
