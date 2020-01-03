package com.example.biblioteca.model.response;

import com.example.biblioteca.model.BookDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoanResponse {
    private String externalId  ;

    private Instant loanDate;

    private Instant returnDate;

    @JsonIgnoreProperties("authors")
    private List<BookDto> loanBooks;

    private String readerExternalId;


}
