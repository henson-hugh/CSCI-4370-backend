package com.csci4050.movie.api.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

import com.csci4050.movie.api.Models.*;

@NoRepositoryBean
public interface UserRepository extends JpaRepository<User, Long> {
    
    //@Query("SELECT u FROM User u WHERE u.user_id = ?1")
    //Optional<User> findUserById(Long id);
}
