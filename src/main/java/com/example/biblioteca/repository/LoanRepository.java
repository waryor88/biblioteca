package com.example.biblioteca.repository;

import com.example.biblioteca.entity.Loan;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends PagingAndSortingRepository<Loan,Long> {

}
