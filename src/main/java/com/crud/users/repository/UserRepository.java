package com.crud.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.users.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}