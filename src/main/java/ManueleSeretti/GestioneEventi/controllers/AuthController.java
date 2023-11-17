package ManueleSeretti.GestioneEventi.controllers;

import ManueleSeretti.GestioneEventi.Entities.User;
import ManueleSeretti.GestioneEventi.exceptions.BadRequestException;
import ManueleSeretti.GestioneEventi.payload.NewUserDTO;
import ManueleSeretti.GestioneEventi.payload.UserLoginDTO;
import ManueleSeretti.GestioneEventi.payload.UserLoginSuccessDTO;
import ManueleSeretti.GestioneEventi.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public UserLoginSuccessDTO login(@RequestBody UserLoginDTO body) {

        return new UserLoginSuccessDTO(authService.authenticateUser(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public User saveUser(@RequestBody @Validated NewUserDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return authService.registerUser(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
