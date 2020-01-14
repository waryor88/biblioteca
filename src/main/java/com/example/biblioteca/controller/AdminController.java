package com.example.biblioteca.controller;

import com.example.biblioteca.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/isAdmin")
@RequiredArgsConstructor
public class AdminController {
  private final AdminService adminService;

  @PostMapping
  public Boolean userIsAdmin() {
    return adminService.userIsAdmin();
  }
}
