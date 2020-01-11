package com.example.biblioteca.service;

import com.example.biblioteca.converter.BookConverter;
import com.example.biblioteca.converter.LoanConverter;
import com.example.biblioteca.entity.Book;
import com.example.biblioteca.entity.BookLoan;
import com.example.biblioteca.entity.Loan;
import com.example.biblioteca.entity.Reader;
import com.example.biblioteca.model.BookDto;
import com.example.biblioteca.model.LoanDto;
import com.example.biblioteca.model.request.BookRequest;
import com.example.biblioteca.model.response.LoanResponse;
import com.example.biblioteca.repository.BookLoanRepository;
import com.example.biblioteca.repository.BookRepository;
import com.example.biblioteca.repository.LoanRepository;
import com.example.biblioteca.repository.ReaderRepository;
import com.exception.MicroserviceException;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanService {

  private final LoanRepository loanRepository;

  private final LoanConverter loanConverter;

  private final BookRepository bookRepository;

  private final BookConverter bookConverter;

  private final ReaderRepository readerRepository;

  private final BookLoanRepository bookLoanRepository;

  public String addLoan(LoanDto loanDto) {

    var email = SecurityContextHolder.getContext().getAuthentication().getName();
    var reader = findReaderByEmail(email);
    List<BookRequest> books = loanDto.getLoanBooks();
    for (BookRequest bookRequest : books) {
      String externalId = UUID.randomUUID().toString();
      loanDto.setExternalId(externalId);
      loanDto.setReaderExternalId(reader.getExternalId());
      loanDto.setLoanDate(Instant.now());
      loanDto.setReturnDate(Instant.now().plusMillis(604800000));
      Loan loan = loanConverter.toEntity(loanDto);
      var bookLoans =
          bookLoanRepository.findBookLoansByBook_ExternalId(bookRequest.getExternalId());
      for (BookLoan bookLoan : bookLoans) {
        loanRepository.save(loan);
      }
      for (BookLoan bookLoan : bookLoans) {
        bookLoan.setLoan(loan);
        bookLoan.setAvailable(false);
        bookLoanRepository.save(bookLoan);
      }
    }
    return "Book succesfully loaned !";
  }

  public List<LoanResponse> getLoans() {
    var email = SecurityContextHolder.getContext().getAuthentication().getName();
    var reader = findReaderByEmail(email);
    var loans = loanRepository.findAllByReaderExternalId(reader.getExternalId());
    List<LoanResponse> loanResponses = new ArrayList<>();
    var books = bookRepository.findBooksByReader(reader.getExternalId());

    for (Loan loan : loans) {
      LoanResponse loanResponse = loanConverter.toResponse(loan);
      for (Book book : books) {
        List<BookDto> bookDtos =
            getBooksByLoanId(loan.getId()).stream()
                .map(this::bookConverter)
                .collect(Collectors.toList());
        loanResponse.setLoanBooks(bookDtos);
      }
      loanResponses.add(loanResponse);
    }

    return loanResponses;
  }

  private BookDto bookConverter(Book book) {
    return bookConverter.toDto(book);
  }

  private List<Book> getBooksByLoanId(Long loanId) {
    return bookRepository.findBookByLoanId(loanId);
  }

  private Reader findReaderByEmail(String email) {
    var reader =
        readerRepository
            .findReaderByEmail(email)
            .orElseThrow(
                () ->
                    new MicroserviceException(
                        HttpStatus.NOT_FOUND, "cannot find reader by email" + email));
    return reader;
  }
}
