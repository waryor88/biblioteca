package com.example.biblioteca.model;

import java.time.Instant;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoanDto {

  private String externalId;

  private Instant loanDate;

  private Instant returnDate;

}
