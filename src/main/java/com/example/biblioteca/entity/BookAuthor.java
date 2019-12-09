package com.example.biblioteca.entity;

import javax.persistence.*;

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

  @ManyToOne(cascade=CascadeType.ALL)
  @JoinColumn
  private Book book;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn
  private Author author;


}
