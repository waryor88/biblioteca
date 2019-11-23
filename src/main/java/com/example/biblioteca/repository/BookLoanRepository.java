package com.example.biblioteca.repository;

import com.example.biblioteca.entity.BookLoan;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookLoanRepository extends PagingAndSortingRepository<BookLoan,Long> {

}
