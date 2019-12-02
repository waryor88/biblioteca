package com.example.biblioteca.repository;

import com.example.biblioteca.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends PagingAndSortingRepository<Author,Long>{

Optional<Author>findByExternalId(String externalId);

}
