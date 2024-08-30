package com.hastycode.registration.service;

import com.hastycode.registration.model.User;
import com.hastycode.registration.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto userRegistrationDto);
}
