package com.example.biblioteca.service;

import com.example.biblioteca.converter.BookConverter;
import com.example.biblioteca.converter.LoanConverter;
import com.example.biblioteca.entity.Book;
import com.example.biblioteca.entity.BookLoan;
import com.example.biblioteca.entity.Loan;
import com.example.biblioteca.model.LoanDto;
import com.example.biblioteca.model.request.BookRequest;
import com.example.biblioteca.repository.BookLoanRepository;
import com.example.biblioteca.repository.BookRepository;
import com.example.biblioteca.repository.LoanRepository;
import com.exception.MicroserviceException;
import java.util.ArrayList;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoanService {

  private final LoanRepository loanRepository;

  private final LoanConverter loanConverter;

  private final BookRepository bookRepository;

  private final BookLoanRepository bookLoanRepository;

  public void addLoan(LoanDto loanDto) {

    List<BookRequest> books = loanDto.getLoanBooks();
    for (BookRequest bookRequest : books) {
      String externalId = UUID.randomUUID().toString();
      loanDto.setExternalId(externalId);
      loanDto.setLoanDate(Instant.now());
      Loan loan = loanConverter.toEntity(loanDto);
    var bookLoans = bookLoanRepository.findBookLoansByBook_ExternalId(bookRequest.getExternalId());
      loanRepository.save(loan);
      for (BookLoan bookLoan : bookLoans) {
        bookLoan.setLoan(loan);
        bookLoan.setAvailable(false);
        bookLoanRepository.save(bookLoan);
      }

    }

  }
}
