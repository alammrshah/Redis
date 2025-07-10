package com.example.practice.ServiceImpl;

import com.example.practice.Entity.User;
import com.example.practice.Repository.UserRepository;
import com.example.practice.Service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public  class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Cacheable(value = "users", key = "#id")
    public User findById(String id) {
        log.info("Get the user from the DB with ID: {}",id);
        return userRepository.findById(id).orElse(null);
    }

    @PostConstruct
    public void init() {
        System.out.println("MyService initialized after dependencies are injected.");
    }
}
