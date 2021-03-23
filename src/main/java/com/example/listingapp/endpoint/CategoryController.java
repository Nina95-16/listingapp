package com.example.listingapp.endpoint;

import com.example.listingapp.exception.ResourceNotFoundException;
import com.example.listingapp.model.Category;
import com.example.listingapp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping()
    public List<Category> getCategory() {
        return categoryService.findAll();
    }

    @GetMapping("{id}")
    public Category getById(@PathVariable("id") int id) throws ResourceNotFoundException {
        return categoryService.getById(id);
    }

    @PostMapping("/add")
    public Category addCategory(@RequestBody Category category) {
        if (category.getId() > 0) {
            throw new RuntimeException("Id must be 0");
        }
        return categoryService.addCategory(category);
    }

    @PutMapping("{id}")
    public Category update(@RequestBody Category category, @PathVariable("id") int id) throws ResourceNotFoundException {
        Category categoryFromDB = categoryService.getById(id);
        categoryFromDB.setName(category.getName());
        return categoryService.addCategory(categoryFromDB);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") int id) throws ResourceNotFoundException {
        if (categoryService.getById(id) == null) {
            return ResponseEntity.notFound().build();
        } else {
            categoryService.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }

}
