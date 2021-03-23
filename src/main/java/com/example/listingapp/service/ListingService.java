package com.example.listingapp.service;

import com.example.listingapp.exception.ResourceNotFoundException;
import com.example.listingapp.model.Listing;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ListingService {


    List<Listing> getListings();

   List<Listing> getByUserEmail(String email) throws ResourceNotFoundException;

    List<Listing> getByCategoryId(int id) throws ResourceNotFoundException;

    void addListing(Listing listing);

    Listing getById(int id);

    void delete(int id);
}
