package com.example.biblioteca.repository;

import com.example.biblioteca.entity.Loan;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends PagingAndSortingRepository<Loan,Long> {

    List<Loan>findAllByReaderExternalId(String readerExternalId);

    Optional<Loan>findLoanByExternalId(String externalId);
}
