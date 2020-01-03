package com.example.biblioteca.repository;

import com.example.biblioteca.entity.Reader;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReaderRepository extends PagingAndSortingRepository<Reader, Long> {

  Optional<Reader> findByExternalId(String externalId);

  @Query(
      value =
          "SELECT re.id,re.address,re.email,re.external_id,re.fname,re.lname,re.tel from library.reader re "
              + "inner join library.users us on re.id=us.reader_id "
              + "where (lower(us.email) LIKE lower(:email))",
      nativeQuery = true)
  Optional<Reader> findReaderByEmail(@Param("email") String email);
}
