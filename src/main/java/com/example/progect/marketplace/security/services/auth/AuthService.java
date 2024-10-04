package com.example.progect.marketplace.security.services.auth;


import com.example.progect.marketplace.security.dto.SignupDTO;
import com.example.progect.marketplace.security.dto.UserDTO;

public interface AuthService {
    UserDTO createUser(SignupDTO signupDTO);
}
