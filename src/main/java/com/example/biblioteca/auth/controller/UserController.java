package com.example.biblioteca.auth.controller;

import com.example.biblioteca.auth.entity.User;
import com.example.biblioteca.auth.oauth2.UserPrincipal;
import com.example.biblioteca.auth.repository.CurrentUser;
import com.example.biblioteca.auth.repository.UserRepository;
import com.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired private UserRepository userRepository;

  @GetMapping("/user/me")
  @PreAuthorize("hasRole('USER')")
  public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
    return userRepository
        .findById(userPrincipal.getId())
        .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
  }
}
