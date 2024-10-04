package com.example.progect.marketplace.security.controllers;

import com.example.progect.marketplace.dto.DtoError;
import com.example.progect.marketplace.security.dto.SignupDTO;
import com.example.progect.marketplace.security.dto.UserDTO;
import com.example.progect.marketplace.security.services.auth.AuthService;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@Schema(name = "Класс регистрации")
@RestController
public class SignupController {

    @Autowired
    private AuthService authService;
    private DtoError dtoError = new DtoError();

    @Schema(name = "Создать новый аккаунт", description = "Необходимо отправить в теле запроса логин и пароль")
    @PostMapping("/sign-up")
    public <T> T signupUser(@Valid @RequestBody SignupDTO signupDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            dtoError.setListError(bindingResult.getAllErrors());
            return (T) dtoError;
        }
        UserDTO createdUser = authService.createUser(signupDTO);
        if (createdUser == null){
            return (T) new ResponseEntity<>("Не получилось создать пользователя", HttpStatus.BAD_REQUEST);
        }
        return (T) new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

}
