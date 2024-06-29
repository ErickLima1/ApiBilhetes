package com.ApiBilhete.ApiBilhetes.controllers;

import com.ApiBilhete.ApiBilhetes.domain.user.AuthenticationDTO;
import com.ApiBilhete.ApiBilhetes.domain.user.LoginResponseDTO;
import com.ApiBilhete.ApiBilhetes.domain.user.RegisterDTO;
import com.ApiBilhete.ApiBilhetes.domain.user.User;
import com.ApiBilhete.ApiBilhetes.infra.security.TokenService;
import com.ApiBilhete.ApiBilhetes.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    //[]Fazer Validation de Email ou Senha Invalida
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    //[]Adicionar Validation para Email Já Cadastrado
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if(this.repository.findByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().body("Escolheu um endereço de correio electrónico que já se encontra registado.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.email(), encryptedPassword, data.role());

        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
