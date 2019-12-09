package com.example.biblioteca.repository;

import com.example.biblioteca.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book,Long> {
    Page<Book>findAll(Pageable pageable);

    @Query("SELECT DISTINCT b from Book b " +
            "WHERE " +
            "(:title IS NOT NULL AND lower(b.title) LIKE %:title%) OR " +
            "(:bookType IS NOT NULL AND lower(b.bookType) LIKE %:bookType%)")
    Page<Book> search(@Param("title") String title,
                        @Param("bookType") String bookType,
                        Pageable pageable);

    Optional<Book> findByExternalId(String externalId);
}
