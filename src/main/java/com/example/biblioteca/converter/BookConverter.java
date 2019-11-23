package com.example.biblioteca.converter;

import com.example.biblioteca.entity.Book;
import com.example.biblioteca.model.BookDto;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface BookConverter {

  BookDto toDto(Book book);
  Book toEntity(BookDto bookDto);

}
