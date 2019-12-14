package com.example.biblioteca.repository;

import com.example.biblioteca.entity.BookLoan;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookLoanRepository extends PagingAndSortingRepository<BookLoan,Long> {

  List<BookLoan>findBookLoansByBook_ExternalId(String bookId);

}
