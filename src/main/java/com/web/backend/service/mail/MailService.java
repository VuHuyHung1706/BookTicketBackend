package com.web.backend.service.mail;

public interface MailService {
    void sendMail(String email);
    Boolean verifyOtp(String email, String otp);
}
