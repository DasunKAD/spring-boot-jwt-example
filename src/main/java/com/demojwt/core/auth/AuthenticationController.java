package com.demojwt.core.auth;

import com.demojwt.core.auth.dao.AuthenticationRequest;
import com.demojwt.core.auth.dao.AuthenticationResponse;
import com.demojwt.core.auth.dao.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest registerRequest
    ){
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest authenticationRequest
    ){
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @PostMapping("/2fa-validate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest authenticationRequest
    ){
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
}
