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
import lombok.RequiredArgsConstructor;
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

  private final BookConverter bookConverter;

  private final BookRepository bookRepository;

  private final BookLoanRepository bookLoanRepository;

  public void addLoan(LoanDto loanDto) {
    List<BookRequest> books = loanDto.getLoanBooks();
    for (BookRequest bookRequest : books) {
      String externalId = UUID.randomUUID().toString();
      loanDto.setExternalId(externalId);
      loanDto.setLoanDate(Instant.now());
      BookLoan bookLoan = new BookLoan();
      Loan loan = loanConverter.toEntity(loanDto);
      bookLoan.setLoan(loan);
      Book book =
          bookRepository
              .findByExternalId(bookRequest.getExternalId())
              .orElseThrow(
                  () ->
                      new MicroserviceException(
                          HttpStatus.NOT_FOUND,
                          "cannot find book by externalId " + bookRequest.getExternalId()));
      bookLoan.setBook(book);
      bookLoan.setInventoryNumber(book.getInventoryNumber());
      loanRepository.save(loan);
      bookLoanRepository.save(bookLoan);
    }
  }
}
