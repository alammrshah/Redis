package com.example.practice;

import com.example.practice.Controller.UserController;
import com.example.practice.Entity.User;
import com.example.practice.Service.UserService;
import com.example.practice.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PracticeApplicationTests {

  @Mock
  private UserService userService;

  @InjectMocks
  private UserController userController;


  @Test
  public void testGetUserById_Success() {
    // Arrange
    User mockUser = new User();
    mockUser.setUserId(1L);
    mockUser.setFirstName("John");
    mockUser.setLastName("Doe");

    when(userService.findById(1L)).thenReturn(mockUser);

    // Act
    ResponseEntity<User> response = userController.getUserById(1L);

    // Assert
    assertEquals(200, response.getStatusCodeValue());
    assertEquals("John", response.getBody().getFirstName());
  }

  @Test
  public void testGetUserById_UserNotFound() {
    // Arrange
    when(userService.findById(999L)).thenThrow(new UserNotFoundException("User not found with id: 999"));

    // Act & Assert
    assertThrows(UserNotFoundException.class, () -> userController.getUserById(999L));
  }
}
