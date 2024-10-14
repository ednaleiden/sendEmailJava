package com.spring.email.sender.SpringbootEmailSender.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfiguration {
    @Value("${email.sender}")
    private String emailUser;

    @Value("${email.password}")
    private String password;

    @Bean
    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");//protocolo
        mailSender.setPort(587);
        mailSender.setUsername(emailUser);
        mailSender.setPassword(password);


        Properties prop = mailSender.getJavaMailProperties();
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls", "true");
        prop.put("mail.debug", "true");

        return mailSender;
    }
}
