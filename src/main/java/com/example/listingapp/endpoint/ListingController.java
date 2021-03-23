package com.example.listingapp.endpoint;

import com.example.listingapp.exception.ResourceNotFoundException;
import com.example.listingapp.model.Listing;
import com.example.listingapp.service.ListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/listings")
@RequiredArgsConstructor
public class ListingController {

    private final ListingService listingService;

    @GetMapping
    public List<Listing> getListings() {
        return listingService.getListings();
    }

    @GetMapping("/byUser/{email}")
    public List<Listing> getByUserEmail(@PathVariable String email) throws ResourceNotFoundException {
        return listingService.getByUserEmail(email);
    }

    @GetMapping("/byCategory/{categoryId}")
    public List<Listing> getByCategoryId(@PathVariable("categoryId") int id) throws ResourceNotFoundException {
        return listingService.getByCategoryId(id);
    }

    @PostMapping
    public void addListing(@RequestBody Listing listing) {
        if (listing.getId() > 0) {
            throw new RuntimeException("Id must be 0");
        }
        listingService.addListing(listing);
    }


    @GetMapping("{id}")
    public Listing byId(@PathVariable("id") int id) throws ResourceNotFoundException {
        return listingService.getById(id);
    }


    @PutMapping("{id}")
    public void update(@RequestBody Listing listing, @PathVariable("id") int id) throws ResourceNotFoundException {
        Listing listing1FromDB = listingService.getById(id);
        listing1FromDB.setTitle(listing.getTitle());
        listing1FromDB.setDescription(listing.getDescription());
        listing1FromDB.setPrice(listing.getPrice());
        listing1FromDB.setCategory(listing.getCategory());
        listing1FromDB.setUser(listing.getUser());
        listingService.addListing(listing1FromDB);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") int id) {
         if (listingService.getById(id)==null){
             return ResponseEntity.notFound().build();
         }
        listingService.delete(id);
        return ResponseEntity.ok().build();
    }

}
