package com.example.springboot_social_network.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailSender {
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String username;

    public MailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendSimpleEmail(String emailTo, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        javaMailSender.send(mailMessage);
    }

    public void sendEmailWithAttachment(String emailTo, String subject, String message, String attachment) throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        FileSystemResource file = new FileSystemResource(ResourceUtils.getFile(attachment));

        messageHelper.setTo(emailTo);
        messageHelper.setSubject(subject);
        messageHelper.setText(message);
        messageHelper.addAttachment("Purchase Order", file);

        javaMailSender.send(mimeMessage);
    }
}