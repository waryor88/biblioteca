package com.example.biblioteca.service;

import com.example.biblioteca.converter.BookConverter;
import com.example.biblioteca.entity.Book;
import com.example.biblioteca.model.BookDto;
import com.example.biblioteca.repository.BookRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookRepository bookRepository;

  private final BookConverter bookConverter;

  public void addBook(BookDto bookDto) {
    bookDto.setExternalId(UUID.randomUUID().toString());
    Book book=bookConverter.toEntity(bookDto);
    bookRepository.save(book);
  }

  public Page<BookDto>getBook(Pageable pageable)
  {
    return bookRepository.findAll(pageable).map(bookConverter::toDto);
  }
}
