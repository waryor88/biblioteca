package com.example.biblioteca.repository;

import com.example.biblioteca.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
  Page<Book> findAll(Pageable pageable);

  @Query(
                      "SELECT DISTINCT b from Book b "
          + "inner join BookAuthor ba on b.id=ba.book.id "
          + "inner join Author a on ba.author.id=a.id "
          + "WHERE "
          + "(:title IS NOT NULL AND lower(b.title) LIKE %:title%) OR "
          + "(:bookType IS NOT NULL AND lower(b.bookType) LIKE %:bookType%) OR "
          + "(trim(lower(CONCAT(a.fname,' ',a.lname))) LIKE trim(lower(:author))) " +
                              "OR lower(a.fname) LIKE %:author% " +
                              "OR lower(a.lname) LIKE %:author% "
          + "OR (concat('',b.year)=:year)")
  Page<Book> search(
      @Param("title") String title,
      @Param("bookType") String bookType,
      @Param("author") String author,
      @Param("year") String year,
      Pageable pageable);

  Optional<Book> findByExternalId(String externalId);

  @Query(
      value =
          "SELECT DISTINCT b.id,b.external_id,b.title,b.year,b.book_type FROM book b " +
                  "inner join book_loan bl on bl.book_id=b.id " +
                  "inner join loan lo on bl.loan_id=lo.id " +
                  " WHERE lo.reader_external_id=:readerId",
      nativeQuery = true)
  List<Book> findBooksByReader(@Param("readerId") String readerId);
}
