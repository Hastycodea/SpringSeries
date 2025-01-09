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
import java.util.List;
import java.util.Optional;

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

    public ReqRes getAllUsers() {
        ReqRes res = new ReqRes();

        try{
            List<OurUsers> result = usersRepo.findAll();

            if(!result.isEmpty()){
                res.setOurUsersList(result);
                res.setStatusCode(200);
                res.setMessage("successful");
            } else {
                res.setStatusCode(404);
                res.setMessage("No users found!");
            }

        } catch (Exception e) {
            res.setStatusCode(500);
            res.setError(e.getMessage());
        }
        return res;
    }

    public ReqRes getUserById(long id) {
        ReqRes res = new ReqRes();
        try{
            OurUsers userById = usersRepo.findById(id).orElseThrow(() -> new RuntimeException("No user found"));
            res.setOurUsers(userById);
            res.setStatusCode(200);
            res.setMessage("User with id '" + id + "' found successfully");

        } catch (Exception e) {
            res.setStatusCode(500);
            res.setError(e.getMessage());
        }
        return  res;
    }

    public ReqRes deleteUser(long id) {
        ReqRes res = new ReqRes();
        try{
            Optional<OurUsers> optionalOurUser = usersRepo.findById(id);
            if (optionalOurUser.isPresent()) {
                usersRepo.deleteById(id);
                res.setStatusCode(200);
                res.setMessage("User of id '" + id + "' deleted successfully");
            } else {
                res.setStatusCode(4040);
                res.setMessage("User not found");
            }
        } catch (Exception e) {
            res.setStatusCode(500);
            res.setError(e.getMessage());
        }
        return res;
    }

    public ReqRes updateUser(long id, OurUsers updatedUser){
        ReqRes res = new ReqRes();

        try{
            Optional<OurUsers> usersOptional = usersRepo.findById(id);

            if(usersOptional.isPresent()) {
                OurUsers existingUser = usersOptional.get();
                existingUser.setEmail(updatedUser.getEmail());
                existingUser.setName(updatedUser.getName());
                existingUser.setCity(updatedUser.getCity());
                existingUser.setRole(updatedUser.getRole());

                if(!updatedUser.getPassword().isEmpty() && updatedUser.getPassword() != null) {
                    existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                }
                OurUsers savedUser = usersRepo.save(existingUser);
                res.setStatusCode(200);
                res.setMessage("User updated successfully");
            } else {
               res.setStatusCode(404);
               res.setMessage("User not found for update");
            }

        } catch (Exception e) {
            res.setStatusCode(500);
            res.setError(e.getMessage());
        }
        return res;
    }

    public ReqRes getMyInfo(String email) {
        ReqRes res = new ReqRes();

        try{
            Optional<OurUsers> usersOptional = usersRepo.findByEmail(email);
            if(usersOptional.isPresent()) {
                res.setOurUsers(usersOptional.get());
                res.setStatusCode(200);
                res.setMessage("successful");
            } else {
                res.setStatusCode(404);
                res.setMessage("Unsuccessful: user not found!");
            }

        } catch (Exception e) {
            res.setStatusCode(500);
            res.setError(e.getMessage());
        }
        return  res;
    }

}
