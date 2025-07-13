package com.example.practice.Utility;

public class CommonConstant {

  // Email related constants
  public static final String EMAIL_SENT_SUCCESS = "Email sent successfully";
  public static final String EMAIL_SUBJECT_WELCOME = "Welcome to Spring Boot Email";
  public static final String EMAIL_BODY_TEST = "Hello, this is a test email from Spring Boot!";
  public static final String EMAIL_RECIPIENT_TEST = "theshahalam63@gmail.com";

  // Error messages
  public static final String USER_NOT_FOUND = "User not found with ID: ";
  public static final String ERROR_USER_NOT_FOUND = "User Not Found";
  public static final String ERROR_BAD_REQUEST = "Bad Request";
  public static final String ERROR_INTERNAL_SERVER = "Internal Server Error";
  public static final String ERROR_UNEXPECTED = "An unexpected error occurred";

  // Log messages
  public static final String LOG_GET_USER_FROM_DB = "Get the user from the DB with ID: {}";
  public static final String LOG_GETTING_ALL_USERS = "Getting all users from the database";
  public static final String LOG_UPDATING_USER = "Updating user with ID: {}";
  public static final String LOG_PATCHING_USER = "Patching user with ID: {}";
  public static final String LOG_DELETING_USER = "Deleting user with ID: {}";
  public static final String LOG_USER_SERVICE_INITIALIZED =
      "UserService initialized after dependencies are injected.";
  public static final String LOG_MAIL_SERVICE_INITIALIZED =
      "MailService initialized with JavaMailSender";
  public static final String LOG_SENDING_EMAIL = "Sending email to: {}, subject: {}";
  public static final String LOG_EMAIL_SENT_SUCCESS = "Email sent successfully to: {}";
  public static final String LOG_EMAIL_SEND_FAILED = "Failed to send email to: {}, error: {}";

  // HTTP response fields
  public static final String RESPONSE_TIMESTAMP = "timestamp";
  public static final String RESPONSE_MESSAGE = "message";
  public static final String RESPONSE_STATUS = "status";
  public static final String RESPONSE_ERROR = "error";
  public static final String RESPONSE_PATH = "path";

  // User fields for patch operations
  public static final String FIELD_FIRST_NAME = "firstName";
  public static final String FIELD_LAST_NAME = "lastName";
  public static final String FIELD_PHONE_NUMBER = "phoneNumber";
  public static final String FIELD_EMAIL = "email";
  public static final String FIELD_DEPARTMENT = "department";
  public static final String FIELD_COURSE = "course";
  public static final String FIELD_BIRTH_DATE = "birthDate";

  // Internationalization
  public static final String I18N_KEY_ALAM = "alam";
  public static final String I18N_DEFAULT_MESSAGE = "Default Message";

  // Request logging
  public static final String LOG_PRE_HANDLE_REQUEST =
      "PreHandlervRequest coming to the Application : ";
  public static final String LOG_POST_HANDLE_REQUEST =
      "PostHandlervRequest coming to the Application : ";
  public static final String LOG_AFTER_COMPLETION = "afterCompletion coming to the Application : ";

  // URI replacement
  public static final String URI_PREFIX = "uri=";
  public static final String EMPTY_STRING = "";

  private CommonConstant() {
    // Private constructor to prevent instantiation
  }
}
