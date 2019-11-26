package com.example.biblioteca.entity;


import java.time.Instant;
import java.util.Set;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  private String externalId;

  private String title;

  private Long year;

  private String inventoryNumber;

  private String bookType;

}
