package com.example.biblioteca.converter;

import com.example.biblioteca.entity.Author;
import com.example.biblioteca.model.AuthorDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AuthorConverter {
  AuthorDto toDto(Author author);

  Author toEntity(AuthorDto authorDto);
}
