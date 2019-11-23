package com.example.biblioteca.repository;

import com.example.biblioteca.entity.Book;
import com.example.biblioteca.entity.BookAuthor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookAuthorRepository extends PagingAndSortingRepository<BookAuthor, Long> {

  @Query(value = "select b.id from library.book b inner join library.book_author ba on b.id=ba.book_id where ba.author_id=:authorId ", nativeQuery = true)
  List<Long> findBookIdByAuthor(@Param("authorId") Long authorId);

}
