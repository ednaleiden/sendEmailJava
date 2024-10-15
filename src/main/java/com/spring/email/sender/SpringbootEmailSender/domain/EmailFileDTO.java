package com.spring.email.sender.SpringbootEmailSender.domain;

import jakarta.mail.Multipart;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;


@Getter //objetos inmutables por eso no deberia ir los setter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailFileDTO {
    private String[] user;
    private String subject;
    private  String message;
    MultipartFile file;
}
