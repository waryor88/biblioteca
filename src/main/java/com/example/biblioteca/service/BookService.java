package com.example.biblioteca.service;

import com.example.biblioteca.converter.AuthorConverter;
import com.example.biblioteca.converter.BookConverter;
import com.example.biblioteca.entity.Book;
import com.example.biblioteca.model.AuthorDto;
import com.example.biblioteca.model.BookDto;
import com.example.biblioteca.repository.AuthorRepository;
import com.example.biblioteca.repository.BookAuthorRepository;
import com.example.biblioteca.repository.BookRepository;
import com.exception.MicroserviceException;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final BookAuthorRepository bookAuthorRepository;

    private final AuthorConverter authorConverter;

    private final BookConverter bookConverter;

    public void addBook(BookDto bookDto) {
        bookDto.setExternalId(UUID.randomUUID().toString());
        Book book = bookConverter.toEntity(bookDto);
        bookRepository.save(book);
    }

    public Page<BookDto> getBook(Pageable pageable) {
        return bookRepository.findAll(pageable).map(this::getAuthors);
    }

    private BookDto getAuthors(Book book) {
        final var bookDto = bookConverter.toDto(book);
        final List<AuthorDto> authors = new ArrayList<>();
        final Iterable<Long> authorIds = bookAuthorRepository.findAuthorsByBookId(book.getId());
        for (Long id : authorIds) {
            AuthorDto authorDto = authorRepository.findById(id)
                    .map(authorConverter::toDto)
                    .orElseThrow(() -> new MicroserviceException(HttpStatus.NOT_FOUND, "cannot find author"));
            authors.add(authorDto);
            bookDto.setAuthors(authors);
        }

        return bookDto;
    }

    public Page<BookDto> search(String criteria, String query, Pageable pageable) {
        if (criteria.isEmpty() || query.isEmpty())
            return bookRepository.findAll(pageable).map(bookConverter::toDto);
        query = query.toLowerCase();
        final var searchFields = criteria.split(",");
        String title = null, bookType = null;
        for (var field : searchFields) {
            switch (field) {
                case "title":
                    title = query;
                    break;
                case "bookType":
                    bookType = query;
                    break;
            }
        }
        return bookRepository.search(title, bookType, pageable).map(bookConverter::toDto);
    }

}
