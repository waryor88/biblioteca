package com.example.biblioteca.auth.controller;

import com.example.biblioteca.auth.entity.AuthProvider;
import com.example.biblioteca.auth.entity.User;
import com.example.biblioteca.auth.model.ApiResponse;
import com.example.biblioteca.auth.model.AuthResponse;
import com.example.biblioteca.auth.model.LoginRequest;
import com.example.biblioteca.auth.model.SignUpRequest;
import com.example.biblioteca.auth.oauth2.TokenProvider;
import com.example.biblioteca.auth.repository.UserRepository;
import com.example.biblioteca.entity.Reader;
import com.example.biblioteca.repository.ReaderRepository;
import com.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private ReaderRepository readerRepository;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        Reader reader=new Reader();
        reader.setExternalId(UUID.randomUUID().toString());
        reader.setAddress(signUpRequest.getAddress());
        reader.setEmail(signUpRequest.getEmail());
        reader.setFname(signUpRequest.getFname());
        reader.setLname(signUpRequest.getLname());
        reader.setTel(signUpRequest.getTel());
        readerRepository.save(reader);

        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setReader(reader);
        User result = userRepository.save(user);



        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully@"));
    }

}
