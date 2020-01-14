package com.example.biblioteca.controller;

import com.example.biblioteca.model.AuthorDto;
import com.example.biblioteca.service.AuthorService;
import lombok.RequiredArgsConstructor;
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
  public List<AuthorDto> getAuthors(Pageable pageable) {
    return authorService.getAuthors(pageable);
  }

  @GetMapping("/{externalId}")
  public AuthorDto getAuthorByExternalId(@PathVariable("externalId") String externalId) {
    return authorService.findByExternalId(externalId);
  }

  @PostMapping("/{authorId}/assign/{bookId}")
  public void authorToBook(
      @PathVariable("authorId") String authorId, @PathVariable("bookId") String bookId) {
    authorService.assignAuthorToBook(authorId, bookId);
  }
}
