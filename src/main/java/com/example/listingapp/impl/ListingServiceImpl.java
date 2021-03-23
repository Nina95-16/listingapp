package com.example.listingapp.impl;

import com.example.listingapp.exception.ResourceNotFoundException;
import com.example.listingapp.model.Category;
import com.example.listingapp.model.Listing;
import com.example.listingapp.repo.CategoryRepo;
import com.example.listingapp.repo.ListingRepo;
import com.example.listingapp.repo.UserRepo;
import com.example.listingapp.service.ListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ListingServiceImpl implements ListingService {

    private final ListingRepo listingRepo;
    private final UserRepo userRepo;
    private final CategoryRepo categoryRepo;

    @Override
    public List<Listing> getListings() {
        return listingRepo.findAll();
    }

    @Override
    public List<Listing> getByUserEmail(String email) throws ResourceNotFoundException {
        boolean userFromDB = userRepo.getByEmail(email) == null;
        if (userFromDB) {
            throw new ResourceNotFoundException("Listing does not exist!");
        } else {
            return listingRepo.getByUserEmail(email);
        }
    }

    @Override
    public void addListing(Listing listing) {
        listingRepo.save(listing);
    }

    @Override
    public List<Listing> getByCategoryId(int id) throws ResourceNotFoundException {
        Optional<Category> byId = categoryRepo.findById(id);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("listing does not exist");
        }
        return listingRepo.getByCategoryId(id);
    }

    @Override
    public void delete(int id) {
        listingRepo.deleteById(id);
    }

    @Override
    public Listing getById(int id) {
        return listingRepo.getById(id);
    }
}
