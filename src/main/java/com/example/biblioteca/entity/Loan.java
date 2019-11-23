package com.example.biblioteca.entity;

import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Loan {
  @Id
  @Generated
  private Long id;

  private String externalId;

  private Instant loanDate;

  private Instant returnDate;
}
