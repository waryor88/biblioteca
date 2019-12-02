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

  private final AuthorConverter authorConverter;

  @PostMapping
  private AuthorDto addAuthor(@RequestBody AuthorDto authorDto) {
    return authorService.addAuthor(authorDto);
  }

  @GetMapping
  private List<AuthorDto> getAuthors(Pageable pageable)
  {
   return authorService.getAuthors(pageable);
  }

@GetMapping("/{externalId}")
  private AuthorDto getAuthorByExternalId(@PathVariable("externalId")String externalId)
{
  return authorService.findByExternalId(externalId);
}

}

