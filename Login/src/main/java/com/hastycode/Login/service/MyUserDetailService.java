package com.hastycode.Login.service;

import com.hastycode.Login.model.UserPrincipal;
import com.hastycode.Login.model.Users;
import com.hastycode.Login.repo.UsersRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@AllArgsConstructor
@NoArgsConstructor
public class MyUserDetailService  implements UserDetailsService {

    private UsersRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = repo.findUserByUsername(username);

        if (user == null) {
            System.out.println("User not found!");
            throw new UsernameNotFoundException("User not found!");
        }

        return new UserPrincipal(user);
    }
}
