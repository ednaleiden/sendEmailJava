package com.spring.email.sender.SpringbootEmailSender.services;

import java.io.File;

public interface IEmailService {

    void sendEmail(String[] user, String subject, String message);

    void sendEmailWithFile(String[] user, String subject, String message, File file);
}
