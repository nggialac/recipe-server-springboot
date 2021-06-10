package com.lacnguyen.recipeserver.api;

import com.lacnguyen.recipeserver.models.EmailRequest;
import com.lacnguyen.recipeserver.service.IMailService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RequestMapping("/api/mail")
@RestController
public class MailApi {

    @Autowired
    private IMailService emailService;

    //this api send simple email
    @PostMapping("/sendingemail")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request) {
        System.out.println(request);
        boolean result = this.emailService.sendEmail(request.getSubject(), request.getMessage(), request.getTo());
        if (result) {
            return ResponseEntity.ok("Email Properly Sent Successfully... ");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("email sending fail");
        }
    }

    //this api send email with html content
    @PostMapping("/sendingemailhtml")
    public ResponseEntity<?> sendEmailHtml(@RequestBody EmailRequest request) {
        System.out.println(request);
        boolean result = this.emailService.sendHtmlTemplate(request.getSubject(), request.getMessage(), request.getTo());
        if (result) {
            return ResponseEntity.ok("Sent Email With HTML template style Successfully... ");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("html template style email sending fail");
        }
    }
}