package com.example.biblioteca.model;

import java.time.Instant;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDto {

  private String externalId;

  private String title;

  private Instant year;

  private String type;


}
