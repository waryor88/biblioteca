package com.example.biblioteca.controller;

import com.example.biblioteca.model.ReaderDto;
import com.example.biblioteca.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/reader")
public class ReaderController {

  private final ReaderService readerService;

  @PostMapping("/register")
  public void addReader(@RequestBody ReaderDto readerDto)
  {
    readerService.addReader(readerDto);
  }

  @GetMapping("/{externalId}")
  public ReaderDto getReaderByExternalId(@PathVariable("externalId")String externalId)
  {
    return readerService.findByExternalId(externalId);
  }
}
