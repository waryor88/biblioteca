package com.example.biblioteca.service;

import com.example.biblioteca.converter.ReaderConverter;
import com.example.biblioteca.entity.Reader;
import com.example.biblioteca.model.ReaderDto;
import com.example.biblioteca.repository.ReaderRepository;
import com.exception.MicroserviceException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReaderService {

  private final ReaderRepository readerRepository;

  private final ReaderConverter readerConverter;

  public void addReader(ReaderDto readerDto) {
    readerDto.setExternalId(UUID.randomUUID().toString());
    Reader reader = readerConverter.toEntity(readerDto);
    readerRepository.save(reader);
  }

  public ReaderDto findByExternalId(String externalId) {
    Reader reader = readerRepository.findByExternalId(externalId)
        .orElseThrow(() -> new MicroserviceException(
            HttpStatus.NOT_FOUND, "cannot find reader with external id " + externalId));
    ReaderDto readerDto = readerConverter.toDto(reader);
    return readerDto;
  }

}
