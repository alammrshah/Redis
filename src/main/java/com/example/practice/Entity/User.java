package com.example.practice.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long userId; // Use Long, not String

  @Column(name = "first_name", nullable = false, length = 50)
  @NotBlank(message = "First name is required")
  private String firstName;

  @Column(name = "last_name", nullable = false, length = 50)
  @NotBlank(message = "Last name is required")
  private String lastName;

  @Column(name = "phone_number", nullable = false, length = 15)
  @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Indian phone number")
  private String phoneNumber;

  @Column(name = "email", nullable = false, unique = true)
  @Email(message = "Email should be valid")
  @NotBlank(message = "Email is required")
  private String email;

  @Column(name = "department")
  private String department;

  @Column(name = "course")
  private String course;

  @Column(name = "birth_date")
  @Past(message = "Birth date must be in the past")
  private LocalDate birthDate;

  @CreationTimestamp
  @Column(name = "created_at", nullable = true, updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;
}
