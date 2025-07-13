package com.example.practice.Service;

import com.example.practice.Entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

  User addUser(User user);

  User findById(Long id);

  java.util.List<User> getAllUsers();

  User updateUser(Long id, User dto);

  User patchUser(Long id, java.util.Map<String, Object> updates);

  void deleteUser(Long id);

  void sendSimpleEmail(String to, String subject, String body);
}
