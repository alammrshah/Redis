package com.example.practice.Utility;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MailService {

  private final JavaMailSender mailSender;

  @Value("${spring.mail.username}")
  private String fromMail;

  // Constructor injection
  public MailService(JavaMailSender mailSender) {
    this.mailSender = mailSender;
    log.info(CommonConstant.LOG_MAIL_SERVICE_INITIALIZED);
  }

  /**
   * Sends a simple email with the specified recipient, subject, and body.
   *
   * @param to the recipient's email address
   * @param subject the subject of the email
   * @param body the body content of the email
   * @throws Exception if an error occurs while sending the email
   */
  public void sendSimpleEmail(String to, String subject, String body) {
    log.info(CommonConstant.LOG_SENDING_EMAIL, to, subject);
    try {
      SimpleMailMessage message = new SimpleMailMessage();
      message.setFrom(fromMail);
      message.setTo(to);
      message.setSubject(subject);
      message.setText(body);
      mailSender.send(message);
      log.info(CommonConstant.LOG_EMAIL_SENT_SUCCESS, to);
    } catch (Exception e) {
      log.error(CommonConstant.LOG_EMAIL_SEND_FAILED, to, e.getMessage());
      throw e;
    }
  }
}
