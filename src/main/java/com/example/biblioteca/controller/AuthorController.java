package com.example.biblioteca.controller;

import com.example.biblioteca.converter.AuthorConverter;
import com.example.biblioteca.entity.Author;
import com.example.biblioteca.model.AuthorDto;
import com.example.biblioteca.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {

  private final AuthorService authorService;

  private final AuthorConverter authorConverter;

  @PostMapping
  private AuthorDto addAuthor(@RequestBody AuthorDto authorDto) {
    return authorService.addAuthor(authorDto);
  }

  @GetMapping
  private Page<AuthorDto>getAuthors(Pageable pageable)
  {
   return authorService.getAuthors(pageable);
  }
}
