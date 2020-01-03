package com.example.biblioteca.controller;

import com.example.biblioteca.model.LoanDto;
import com.example.biblioteca.model.response.LoanResponse;
import com.example.biblioteca.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<LoanResponse>getLoans(){
        return loanService.getLoans();
    }

}
