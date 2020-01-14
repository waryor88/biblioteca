package com.example.biblioteca.controller;

import com.example.biblioteca.model.BookDto;
import com.example.biblioteca.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

  private final BookService bookService;

  @PostMapping
  public void addBook(@RequestBody BookDto bookDto) {
    bookService.addBook(bookDto);
  }

  @GetMapping
  public List<BookDto> getBooks(Pageable pageable) {
    return bookService.getBook(pageable);
  }

  @GetMapping("/search")
  public List<BookDto> search(
      @RequestParam(name = "criteria", required = false) String criteria,
      @RequestParam(name = "query", required = false) String query,
      Pageable pageable) {
    return bookService.search(criteria, query, pageable);
  }

  @GetMapping("/{externalId}")
  public BookDto getBookByExternalId(@PathVariable("externalId") String externalId) {
    return bookService.getBookByExternalId(externalId);
  }

  @GetMapping("/loan/{externalId}")
  public BookDto getBookByLoanExternalId(@PathVariable("externalId") String externalId) {
    return bookService.getBookByLoanExternalId(externalId);
  }

  @PutMapping
  public void updateBook(@RequestBody BookDto bookDto) {
    bookService.updateBookByExternalId(bookDto);
  }


  @DeleteMapping("/delete/{externalId}")
  public String deleteBook(@PathVariable("externalId")String externalId)
  {
    return bookService.deleteBookByExternalId(externalId);
  }

}
