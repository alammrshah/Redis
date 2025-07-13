package com.example.practice.Controller;

import com.example.practice.Entity.User;
import com.example.practice.Service.UserService;
import com.example.practice.Utility.CommonConstant;
import jakarta.validation.Valid;
import java.util.*;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;
  private final MessageSource messageSource;

  // Constructor injection
  public UserController(UserService userService, MessageSource messageSource) {
    this.userService = userService;
    this.messageSource = messageSource;
  }

  // CREATE - POST
  @PostMapping
  public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
    User createdUser = userService.addUser(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
  }

  // READ - GET all
  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = userService.getAllUsers();
    return ResponseEntity.ok(users);
  }

  // READ - GET by ID
  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    User user = userService.findById(id);
    return ResponseEntity.ok(user);
  }

  // UPDATE - PUT (full update)
  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(
      @PathVariable Long id, @Valid @RequestBody User updatedUser) {
    User user = userService.updateUser(id, updatedUser);
    return ResponseEntity.ok(user);
  }

  // PATCH - Partial update
  @PatchMapping("/{id}")
  public ResponseEntity<User> patchUser(
      @PathVariable Long id, @RequestBody Map<String, Object> updates) {
    User user = userService.patchUser(id, updates);
    return ResponseEntity.ok(user);
  }

  // DELETE
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/send")
  public ResponseEntity<String> sendEmail() {
    userService.sendSimpleEmail(
        CommonConstant.EMAIL_RECIPIENT_TEST,
        CommonConstant.EMAIL_SUBJECT_WELCOME,
        CommonConstant.EMAIL_BODY_TEST);
    return ResponseEntity.ok(CommonConstant.EMAIL_SENT_SUCCESS);
  }

  @GetMapping("/internationalization")
  public ResponseEntity<String> getInternationalizationName() {
    Locale locale = LocaleContextHolder.getLocale();
    String message =
        messageSource.getMessage(
            CommonConstant.I18N_KEY_ALAM, null, CommonConstant.I18N_DEFAULT_MESSAGE, locale);
    return ResponseEntity.ok(message);
  }
}
