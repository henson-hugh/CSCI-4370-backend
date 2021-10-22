package com.csci4050.movie.api.Repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.csci4050.movie.api.Models.*;

@Repository
public interface AdminRepository extends UserRepository{
    
    //@Query("SELECT a FROM Admin a WHERE a.user_id = ?1")
    //Optional<Admin> findAdminById(Long id);
}
