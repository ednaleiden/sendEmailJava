package com.spring.email.sender.SpringbootEmailSender.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Service
public class EmailServiceImpl implements IEmailService{

    @Value("${email.sender}")
    private String emailUser;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(String[] user, String subject, String message) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(emailUser);
        mailMessage.setTo(user);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }

    @Override
    public void sendEmailWithFile(String[] user, String subject, String message, File file) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true, StandardCharsets.UTF_8.name());
            mimeMessageHelper.setFrom(emailUser);
            mimeMessageHelper.setTo(user);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(message);
            mimeMessageHelper.addAttachment(file.getName(),file);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
