package com.example.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
  private String username;

  private String password;

  private String externalId;
}
