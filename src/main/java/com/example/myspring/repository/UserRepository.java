package com.example.myspring.repository;

import com.example.myspring.domain.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<Users, Long> {
    List<Users> findByName(String name);
}
