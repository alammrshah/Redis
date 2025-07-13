package com.example.practice.ServiceImpl;

import com.example.practice.Entity.User;
import com.example.practice.Repository.UserRepository;
import com.example.practice.Service.UserService;
import com.example.practice.Utility.CommonConstant;
import com.example.practice.Utility.MailService;
import com.example.practice.exception.UserNotFoundException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final MailService mailService;

  // Constructor injection
  public UserServiceImpl(UserRepository userRepository, MailService mailService) {
    this.userRepository = userRepository;
    this.mailService = mailService;
  }

  @Override
  @Transactional
  public User addUser(User user) {
    return userRepository.save(user);
  }

  @Override
  @Cacheable(value = "users", key = "#id")
  public User findById(Long id) {
    log.info(CommonConstant.LOG_GET_USER_FROM_DB, id);
    return userRepository
        .findById(id)
        .orElseThrow(() -> new UserNotFoundException(CommonConstant.USER_NOT_FOUND + id));
  }

  @Override
  public java.util.List<User> getAllUsers() {
    log.info(CommonConstant.LOG_GETTING_ALL_USERS);
    return userRepository.findAll();
  }

  @Override
  @Transactional
  public User updateUser(Long id, User dto) {
    log.info(CommonConstant.LOG_UPDATING_USER, id);
    User existingUser =
        userRepository
            .findById(id)
            .orElseThrow(() -> new UserNotFoundException(CommonConstant.USER_NOT_FOUND + id));

    // Update fields from DTO
    if (dto.getFirstName() != null) {
      existingUser.setFirstName(dto.getFirstName());
    }
    if (dto.getLastName() != null) {
      existingUser.setLastName(dto.getLastName());
    }
    if (dto.getEmail() != null) {
      existingUser.setEmail(dto.getEmail());
    }
    if (dto.getPhoneNumber() != null) {
      existingUser.setPhoneNumber(dto.getPhoneNumber());
    }
    if (dto.getDepartment() != null) {
      existingUser.setDepartment(dto.getDepartment());
    }
    if (dto.getCourse() != null) {
      existingUser.setCourse(dto.getCourse());
    }
    if (dto.getBirthDate() != null) {
      existingUser.setBirthDate(dto.getBirthDate());
    }

    return userRepository.save(existingUser);
  }

  @Override
  @Transactional
  public User patchUser(Long id, java.util.Map<String, Object> updates) {
    log.info(CommonConstant.LOG_PATCHING_USER, id);
    User existingUser =
        userRepository
            .findById(id)
            .orElseThrow(() -> new UserNotFoundException(CommonConstant.USER_NOT_FOUND + id));

    updates.forEach(
        (key, value) -> {
          switch (key) {
            case CommonConstant.FIELD_FIRST_NAME -> existingUser.setFirstName((String) value);
            case CommonConstant.FIELD_LAST_NAME -> existingUser.setLastName((String) value);
            case CommonConstant.FIELD_PHONE_NUMBER -> existingUser.setPhoneNumber((String) value);
            case CommonConstant.FIELD_EMAIL -> existingUser.setEmail((String) value);
            case CommonConstant.FIELD_DEPARTMENT -> existingUser.setDepartment((String) value);
            case CommonConstant.FIELD_COURSE -> existingUser.setCourse((String) value);
            case CommonConstant.FIELD_BIRTH_DATE ->
                existingUser.setBirthDate(java.time.LocalDate.parse((String) value));
          }
        });

    return userRepository.save(existingUser);
  }

  @Override
  @Transactional
  public void deleteUser(Long id) {
    log.info(CommonConstant.LOG_DELETING_USER, id);
    User existingUser =
        userRepository
            .findById(id)
            .orElseThrow(() -> new UserNotFoundException(CommonConstant.USER_NOT_FOUND + id));
    userRepository.delete(existingUser);
  }

  @Override
  public void sendSimpleEmail(String to, String subject, String body) {
    mailService.sendSimpleEmail(to, subject, body);
  }

  @PostConstruct
  public void init() {
    log.info(CommonConstant.LOG_USER_SERVICE_INITIALIZED);
  }
}
