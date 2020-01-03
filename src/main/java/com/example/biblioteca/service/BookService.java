package com.example.biblioteca.service;

import com.example.biblioteca.converter.AuthorConverter;
import com.example.biblioteca.converter.BookConverter;
import com.example.biblioteca.entity.Book;
import com.example.biblioteca.entity.BookLoan;
import com.example.biblioteca.model.AuthorDto;
import com.example.biblioteca.model.BookDto;
import com.example.biblioteca.repository.AuthorRepository;
import com.example.biblioteca.repository.BookAuthorRepository;
import com.example.biblioteca.repository.BookLoanRepository;
import com.example.biblioteca.repository.BookRepository;
import com.exception.MicroserviceException;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookRepository bookRepository;

  private final AuthorRepository authorRepository;

  private final BookLoanRepository bookLoanRepository;

  private final BookAuthorRepository bookAuthorRepository;

  private final AuthorConverter authorConverter;

  private final BookConverter bookConverter;

  public BookDto addBook(BookDto bookDto) {
    bookDto.setExternalId(UUID.randomUUID().toString());
    Book book = bookConverter.toEntity(bookDto);
    BookLoan bookLoan = new BookLoan();
    Random invNumber = new Random();
    bookLoan.setInventoryNumber((long) invNumber.nextInt(123123));
    bookLoan.setAvailable(true);
    bookLoan.setBook(book);
    bookRepository.save(book);
    bookLoanRepository.save(bookLoan);
    return bookDto;
  }

  public List<BookDto> getBook(Pageable pageable) {
    List<BookDto> bookDtos = new ArrayList<>();
    Page<BookDto> bookDtoPage = bookRepository.findAll(pageable).map(this::getAuthors);
    for (BookDto bookDto : bookDtoPage) {
      bookDtos.add(bookDto);
    }
    return bookDtos;
  }

  private BookDto getAuthors(Book book) {
    final var bookDto = bookConverter.toDto(book);
    final List<AuthorDto> authors = new ArrayList<>();
    final Iterable<Long> authorIds = bookAuthorRepository.findAuthorsByBookId(book.getId());
    for (Long id : authorIds) {
      AuthorDto authorDto =
          authorRepository
              .findById(id)
              .map(authorConverter::toDto)
              .orElseThrow(
                  () -> new MicroserviceException(HttpStatus.NOT_FOUND, "cannot find author"));
      authors.add(authorDto);

  }
    bookDto.setAuthors(authors);
    return bookDto;
  }

  public List<BookDto> search(String criteria, String query, Pageable pageable) {
      final List<BookDto>books=new ArrayList<>();
    if (criteria.isEmpty() || query.isEmpty()) {
      return getBook(pageable);
    }
    query = query.toLowerCase();
    final var searchFields = criteria.split(",");
    String title = null, bookType = null,author=null,year=null;

    for (var field : searchFields) {
      switch (field) {
        case "title":
          title = query;
          break;
          case "author":
              author = query;
              break;
          case "year":
              year = query;
              break;
        case "bookType":
          bookType = query;
          break;
      }
    }
    Page<BookDto>bookDtoPage= bookRepository.search(title, bookType,author,year, pageable).map(this::getAuthors);
    for(BookDto bookDto :bookDtoPage)
    {
        books.add(bookDto);
    }
    return books;
  }
}
