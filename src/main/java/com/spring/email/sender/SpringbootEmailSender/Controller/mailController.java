package com.spring.email.sender.SpringbootEmailSender.Controller;

import com.spring.email.sender.SpringbootEmailSender.domain.EmailDTO;
import com.spring.email.sender.SpringbootEmailSender.domain.EmailFileDTO;
import com.spring.email.sender.SpringbootEmailSender.services.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

//@RequestBody json convertido a un objeto
@RestController
@RequestMapping("api/v0/email")
public class mailController {

    @Autowired
    private IEmailService emailService;

    @PostMapping("/sendMessage")
    public ResponseEntity<?> reciveRequestEmail(@RequestBody EmailDTO emailDTO){
        System.out.print("mensaje recibido" + emailDTO);

        emailService.sendEmail(emailDTO.getUser(), emailDTO.getSubject(), emailDTO.getMessage());

        Map<String, String> response = new HashMap<>();
        response.put("estado", "Enviado");
        return  ResponseEntity.ok(response);
    }

    @PostMapping("/sendMessageFile")
    public ResponseEntity<?> reciveRequestEmailWithFile(@ModelAttribute EmailFileDTO emailFileDTO){
       try{
           String filename = emailFileDTO.getFile().getName();
           Path path = Paths.get("src/mail/resources/files/" + filename);

           Files.createDirectories(path.getParent());
           Files.copy(emailFileDTO.getFile().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

           File file = path.toFile();

           emailService.sendEmailWithFile(emailFileDTO.getUser(), emailFileDTO.getSubject(), emailFileDTO.getMessage(), file);

           Map<String, String> response = new HashMap<>();
           response.put("estado", "Enviado");
           response.put("archivo", filename);

           return ResponseEntity.ok(response);

       }catch (Exception e){
        throw new RuntimeException("Error al enviar el email con el archivo" + e.getMessage());
       }

    }
}
