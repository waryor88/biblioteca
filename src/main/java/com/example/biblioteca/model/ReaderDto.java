package com.example.biblioteca.model;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReaderDto {

  private String externalId;

  private String fname;

  private String lname;

  private String tel;

  private String address;

  private String email;

  private List<BookDto>loanBooks;

}
