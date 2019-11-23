package com.example.biblioteca.service;

import com.example.biblioteca.converter.AuthorConverter;
import com.example.biblioteca.converter.BookConverter;
import com.example.biblioteca.entity.Author;
import com.example.biblioteca.entity.Book;
import com.example.biblioteca.model.AuthorDto;
import com.example.biblioteca.model.BookDto;
import com.example.biblioteca.repository.AuthorRepository;
import com.example.biblioteca.repository.BookAuthorRepository;
import com.example.biblioteca.repository.BookRepository;
import com.exception.MicroserviceException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorConverter authorConverter;

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    private final BookConverter bookConverter;

    private final BookAuthorRepository bookAuthorRepository;

    public AuthorDto addAuthor(AuthorDto authorDto) {
        authorDto.setExternalId(UUID.randomUUID().toString());
        Author author = authorConverter.toEntity(authorDto);
        authorRepository.save(author);
        return authorDto;
    }

    public Page<AuthorDto> getAuthors(Pageable pageable) {
        return authorRepository.findAll(pageable).map(this::fetchMapping);
    }

    private AuthorDto fetchMapping(Author author) {
        final List<BookDto> bookDtos = new ArrayList<>();
        final Iterable<Long> bookIds = bookAuthorRepository.findBookIdByAuthor(author.getId());
        final AuthorDto authorDto = authorConverter.toDto(author);
        for (Long bookId : bookIds) {
          Book book = bookRepository.findById(bookId).orElseThrow(()->new MicroserviceException(HttpStatus.NOT_FOUND,"canno find book"));
            BookDto bookDto = bookConverter.toDto(book);
            bookDtos.add(bookDto);
            authorDto.setBooks(bookDtos);
        }
        return authorDto;
    }

}
