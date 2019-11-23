package com.example.biblioteca.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookLoan {

  @Id
  @Generated
  private Long id;

  @ManyToOne
  private Book book;

  @ManyToOne
  private Loan loan;
}
