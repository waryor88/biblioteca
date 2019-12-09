package com.example.biblioteca.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthorDto {

  private String externalId;

  private String fname;

  private String lname;

  private String address;

  private String tel;

  private String email;

  private List<BookDto> books;
}
