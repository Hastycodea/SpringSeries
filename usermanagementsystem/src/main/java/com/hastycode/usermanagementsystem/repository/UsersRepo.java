package com.hastycode.usermanagementsystem.repository;

import com.hastycode.usermanagementsystem.entity.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<OurUsers, Long> {

    Optional<OurUsers> findByEmail(String email);

}
