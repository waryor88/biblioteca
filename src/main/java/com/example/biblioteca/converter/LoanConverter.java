package com.example.biblioteca.converter;

import com.example.biblioteca.entity.Loan;
import com.example.biblioteca.model.LoanDto;
import com.example.biblioteca.model.response.LoanResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoanConverter {

  LoanDto toDto(Loan loan);

  Loan toEntity(LoanDto loanDto);

  LoanResponse toResponse(Loan loan);

}
