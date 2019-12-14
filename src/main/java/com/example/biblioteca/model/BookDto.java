package com.example.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDto {

  private String externalId;

  private String title;

  private Integer year;

  private String bookType;

  private List<AuthorDto>authors;



}
