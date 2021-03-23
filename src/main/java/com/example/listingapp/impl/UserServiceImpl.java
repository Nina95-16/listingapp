package com.example.listingapp.impl;

import com.example.listingapp.exception.ResourceNotFoundException;
import com.example.listingapp.model.User;
import com.example.listingapp.repo.UserRepo;
import com.example.listingapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Override
    public User addUser(User user) {
        return userRepo.save(user);
    }

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getById(int id) throws ResourceNotFoundException {
        return userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with" + id + " does not exist"));
    }

    @Override
    public void delete(int id) {
        userRepo.deleteById(id);
    }
}
