package com.example.biblioteca.repository;

import com.example.biblioteca.entity.Reader;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepository extends PagingAndSortingRepository<Reader,Long> {

}
