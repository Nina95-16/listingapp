package com.example.listingapp.impl;

import com.example.listingapp.exception.ResourceNotFoundException;
import com.example.listingapp.model.Category;
import com.example.listingapp.repo.CategoryRepo;
import com.example.listingapp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Category getById(int id) throws ResourceNotFoundException {
        return categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Categories with " + id + "does not exist"));
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public void deleteById(int id) {
        categoryRepo.deleteById(id);
    }

}
