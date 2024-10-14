package com.spring.email.sender.SpringbootEmailSender.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter //objetos inmutables por eso no deberia ir los setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailDTO {

    private String[] user;
    private String subject;
    private  String message;

}
