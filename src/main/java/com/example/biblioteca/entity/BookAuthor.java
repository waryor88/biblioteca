package com.example.biblioteca.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookAuthor {

  @Id
  @Generated
  private Long id;

  @ManyToOne
  @JoinColumn
  private Book book;

  @ManyToOne
  @JoinColumn
  private Author author;


}
