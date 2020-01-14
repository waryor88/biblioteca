package com.example.biblioteca.service;

import com.example.biblioteca.repository.AdminRepository;
import com.exception.MicroserviceException;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
  private final AdminRepository userRepository;

  public Boolean userIsAdmin() {
    var email = SecurityContextHolder.getContext().getAuthentication().getName();
    var user =
        userRepository
            .findUserByEmail(email)
            .orElseThrow(
                () ->
                    new MicroserviceException(
                        HttpStatus.NOT_FOUND, "cannot find user by email " + email));
    if (user.getIsAdmin() == true) return true;
    else return false;
  }
}
