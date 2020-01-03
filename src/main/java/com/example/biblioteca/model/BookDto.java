package com.example.biblioteca.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDto {

  private String externalId;

  private String title;

  private Integer year;

  private String bookType;

  @JsonIgnoreProperties("books")
  private List<AuthorDto>authors=new ArrayList<>();


}
