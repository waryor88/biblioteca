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
public class BookLoan {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Boolean available;

  private Long inventoryNumber;

  @ManyToOne(cascade = CascadeType.ALL)
  private Book book;

  @ManyToOne(cascade = CascadeType.ALL)
  private Loan loan;


}
