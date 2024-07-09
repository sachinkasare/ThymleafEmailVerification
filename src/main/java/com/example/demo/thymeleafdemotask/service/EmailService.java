package com.example.demo.thymeleafdemotask.service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;

@Service
public class EmailService {

    @Autowired
    JavaMailSender mailSender;

    public void sendmailAttachment(String to,String[] cc,String subject,String body,String filepath) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body);
        helper.setCc(cc);
        helper.setFrom("sachinka417@gmail.com");
        FileSystemResource file = new FileSystemResource(new File(filepath));
        helper.addAttachment(Objects.requireNonNull(file.getFilename()),file);

        mailSender.send(mimeMessage);

    }
}