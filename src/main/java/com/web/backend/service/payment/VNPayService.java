package com.web.backend.service.payment;

import com.web.backend.dto.request.payment.PaymentRequest;
import com.web.backend.dto.response.payment.PaymentResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface VNPayService {
    PaymentResponse createPayment(PaymentRequest request, HttpServletRequest httpRequest);
    boolean processPaymentCallback(HttpServletRequest request);
}
