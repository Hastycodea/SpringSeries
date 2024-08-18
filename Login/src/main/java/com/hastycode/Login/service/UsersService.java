package com.hastycode.Login.service;

import com.hastycode.Login.repo.UsersRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UsersService {

    private UsersRepo repo;


}
