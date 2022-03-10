package com.example.RinconesMendoza.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServicio {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarMail(String destinatario, String asunto, String contenido) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(destinatario);
        simpleMailMessage.setSubject(asunto);
        simpleMailMessage.setText(contenido);
        mailSender.send(simpleMailMessage);
    }
}
