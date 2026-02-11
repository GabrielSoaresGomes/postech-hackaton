package com.postech.hackaton.interface_adapter.gateways;

import com.postech.hackaton.application.gateways.EmailGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.mail.SimpleMailMessage;

@Component
@RequiredArgsConstructor
@Slf4j
public class MailHogEmailGatewayImpl implements EmailGateway {

    private final JavaMailSender mailSender;

    @Override
    public void send(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            message.setFrom("noreply@postech.local");

            mailSender.send(message);
            log.info("Email enviado para {} com assunto={}", to, subject);
        } catch (Exception e) {
            log.error("Falha ao enviar email para {}: {}", to, e.getMessage(), e);
        }
    }
}

