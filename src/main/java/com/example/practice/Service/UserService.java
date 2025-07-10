package com.example.practice.Service;

import com.example.practice.Entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User addUser(User user);
    User findById(String Id);


}
