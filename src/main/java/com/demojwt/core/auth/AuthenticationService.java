package com.demojwt.core.auth;

import com.demojwt.core.auth.dao.AuthenticationRequest;
import com.demojwt.core.auth.dao.AuthenticationResponse;
import com.demojwt.core.auth.dao.RegisterRequest;
import com.demojwt.core.model.User;
import com.demojwt.core.repository.UserRepository;
import com.demojwt.core.security.JwtService;
import com.demojwt.core.util.SystemConfiguration;
import com.demojwt.core.util.SystemUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final SystemConfiguration systemConfiguration;
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = User.builder()
                .firstName(registerRequest.getName())
                .email(registerRequest.getEmail())
                .username(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .authPin(SystemUtil.pinGenerator())
                .build();

        //Send 2FA on Registration
        SystemUtil.pinGenerator();
        User savedUser = userRepository.save(user);


        var token = jwtService.generateToken(user, systemConfiguration);
        return AuthenticationResponse.builder()
                .token(token)
                .roles(SystemUtil.userRoles(user))
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserName(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByUsername(request.getUserName()).orElseThrow(() -> new UsernameNotFoundException("Invalid Username"));
        user.setAuthPin(SystemUtil.pinGenerator());
        userRepository.save(user);
        var token = jwtService.generateToken(user, systemConfiguration);
        return AuthenticationResponse.builder()
                .token(token)
                .roles(SystemUtil.userRoles(user))
                .build();
    }
}
