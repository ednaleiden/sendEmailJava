package com.spring.email.sender.SpringbootEmailSender.domain;

import jakarta.mail.Multipart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;


@Getter //objetos inmutables por eso no deberia ir los setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailFileDTO {
    private String[] user;
    private String subject;
    private  String message;
    MultipartFile file;
}
