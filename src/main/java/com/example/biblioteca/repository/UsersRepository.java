package com.example.biblioteca.repository;

import com.example.biblioteca.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {
	Users findByUserName(String username);
	
}
