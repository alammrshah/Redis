package com.example.practice.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

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

    // --- Constructors ---

    public User() {}

    public User(String userId, String firstName, String lastName, String phoneNumber, String email,
                String department, String course, LocalDate birthDate) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.department = department;
        this.course = course;
        this.birthDate = birthDate;
    }

    // --- Getters and Setters ---

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    // --- toString() ---

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", course='" + course + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
