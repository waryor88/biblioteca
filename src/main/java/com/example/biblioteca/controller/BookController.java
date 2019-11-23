package com.example.biblioteca.controller;

import com.example.biblioteca.converter.BookConverter;
import com.example.biblioteca.entity.Book;
import com.example.biblioteca.model.BookDto;
import com.example.biblioteca.repository.BookRepository;
import com.example.biblioteca.service.BookService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {
  private final BookService bookService;

  private final BookConverter bookConverter;

  private final BookRepository bookRepository;

  @PostMapping
  private void addBook(@RequestBody BookDto bookDto)
  {
    bookDto.setExternalId(UUID.randomUUID().toString());
    Book book=bookConverter.toEntity(bookDto);
    bookRepository.save(book);
  }

}
