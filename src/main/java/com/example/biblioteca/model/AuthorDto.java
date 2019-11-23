package com.example.biblioteca.model;

import com.example.biblioteca.entity.Book;
import java.util.List;
import javax.persistence.Entity;
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
