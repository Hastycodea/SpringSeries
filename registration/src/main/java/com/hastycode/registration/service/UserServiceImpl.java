package com.hastycode.registration.service;

import com.hastycode.registration.model.Role;
import com.hastycode.registration.model.User;
import com.hastycode.registration.model.UserPrincipal;
import com.hastycode.registration.repo.UserRepo;
import com.hastycode.registration.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService{

    private UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User save(UserRegistrationDto userRegistrationDto) {
        User user = new User(userRegistrationDto.getFirstName(), userRegistrationDto.getLastName(),
                userRegistrationDto.getEmail(), userRegistrationDto.getPassword(), Arrays.asList(new Role("ROLE_USER")));
        return userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new UserPrincipal(user);
    }
}
