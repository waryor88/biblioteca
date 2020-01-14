package com.example.biblioteca.converter;

import com.example.biblioteca.entity.Reader;
import com.example.biblioteca.model.ReaderDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReaderConverter {

  ReaderDto toDto(Reader reader);
  Reader toEntity(ReaderDto readerDto);



}
