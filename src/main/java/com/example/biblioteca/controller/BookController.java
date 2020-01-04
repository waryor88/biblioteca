package com.example.biblioteca.controller;


import com.example.biblioteca.model.BookDto;
import com.example.biblioteca.service.BookService;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

  private final BookService bookService;

  @PostMapping
  public void addBook(@RequestBody BookDto bookDto)
  {
   bookService.addBook(bookDto);
  }

  @GetMapping
  public List<BookDto> getBooks(Pageable pageable) {
    return bookService.getBook(pageable);
  }

  @GetMapping("/search")
  public List<BookDto> search(@RequestParam(name = "criteria", required = false) String criteria,
                                @RequestParam(name = "query", required = false) String query, Pageable pageable) {
    return bookService.search(criteria, query, pageable);
  }

}
