package com.example.biblioteca.controller;

import com.example.biblioteca.converter.AuthorConverter;
import com.example.biblioteca.entity.Author;
import com.example.biblioteca.model.AuthorDto;
import com.example.biblioteca.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {

  private final AuthorService authorService;

  @PostMapping
  public AuthorDto addAuthor(@RequestBody AuthorDto authorDto) {
    return authorService.addAuthor(authorDto);
  }

  @GetMapping
  public List<AuthorDto> getAuthors(Pageable pageable)
  {
   return authorService.getAuthors(pageable);
  }

@GetMapping("/{externalId}")
  public AuthorDto getAuthorByExternalId(@PathVariable("externalId")String externalId)
{
  return authorService.findByExternalId(externalId);
}

}

