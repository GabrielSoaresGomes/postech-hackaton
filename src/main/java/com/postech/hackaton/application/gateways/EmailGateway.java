package com.postech.hackaton.application.gateways;

public interface EmailGateway {
    void send(String to, String subject, String body);
}

