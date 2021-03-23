package com.example.listingapp.service;

import com.example.listingapp.exception.ResourceNotFoundException;
import com.example.listingapp.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    List<Category> findAll();

    Category getById(int id) throws ResourceNotFoundException;

    Category addCategory(Category category);

    void deleteById(int id);
}
