package com.hastycode.usermanagementsystem.service;

import com.hastycode.usermanagementsystem.dto.ReqRes;
import com.hastycode.usermanagementsystem.entity.OurUsers;
import com.hastycode.usermanagementsystem.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserManagementService {

    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ReqRes register(ReqRes registrationRequest) {
        ReqRes resp = new ReqRes();
        try{
            OurUsers ourUser = new OurUsers();
            ourUser.setEmail(registrationRequest.getEmail());
            ourUser.setName(registrationRequest.getName());
            ourUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            ourUser.setCity(registrationRequest.getCity());
            ourUser.setRole(registrationRequest.getRole());

            OurUsers ourUserResult = usersRepo.save(ourUser);

            if(ourUserResult.getId() > 0) {
                resp.setOurUsers(ourUserResult);
                resp.setMessage("User saved successfully");
                resp.setStatusCode(200);
            }

        }catch (Exception e) {
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    public ReqRes login(ReqRes loginRequest) {
        ReqRes response = new ReqRes();
        try{
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            var user = usersRepo.findByEmail(loginRequest.getEmail()).orElseThrow();
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setExpirationTime("24Hrs");
            response.setRefreshToken(refreshToken);
            response.setRole(user.getRole());
            response.setMessage("Successfully logged in");

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }

    public ReqRes refreshToken(ReqRes refreshTokenRequest) {
        ReqRes response = new ReqRes();

        try{
            String ourEmail = jwtUtils.extractUsername(refreshTokenRequest.getToken());
            OurUsers user = usersRepo.findByEmail(ourEmail).orElseThrow();
           if(jwtUtils.isTokenValid(refreshTokenRequest.getToken(), user)) {
               var jwt = jwtUtils.generateToken(user);
               response.setStatusCode(200);
               response.setToken(jwt);
               response.setExpirationTime("24Hrs");
               response.setRefreshToken(refreshTokenRequest.getToken());
               response.setMessage("Successfully refreshed token");
           }

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }

        return response;

    }

}
