package com.example.biblioteca.repository;

import com.example.biblioteca.auth.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface AdminRepository extends PagingAndSortingRepository<User, Long> {
  Optional<User> findUserByEmail(String email);
}
