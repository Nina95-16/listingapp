package com.example.listingapp.repo;

import com.example.listingapp.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListingRepo extends JpaRepository<Listing, Integer> {


  List<Listing> getByUserEmail(String email);
   List<Listing> getByCategoryId(int id);
   Listing getById(int id);
}
