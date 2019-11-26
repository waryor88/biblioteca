package com.example.biblioteca.model;

import java.time.Instant;
import java.util.List;
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

  private List<BookDto>loanBooks;

  private ReaderDto reader;

}
