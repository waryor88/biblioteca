package com.example.biblioteca.controller;

import com.example.biblioteca.model.LoanDto;
import com.example.biblioteca.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/loan")
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public void addLoan(@RequestBody LoanDto loanDto)
    {
        loanService.addLoan(loanDto);
    }

}
