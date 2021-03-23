package com.example.listingapp.repo;

import com.example.listingapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository <User,Integer>{
   User getByEmail(String email);
}
