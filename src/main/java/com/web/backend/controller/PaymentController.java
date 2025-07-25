package com.web.backend.controller;

import com.web.backend.dto.request.payment.PaymentRequest;
import com.web.backend.dto.response.ApiResponse;
import com.web.backend.dto.response.payment.PaymentResponse;
import com.web.backend.service.payment.VNPayService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@SecurityRequirement(name = "Bearer")
public class PaymentController {

    @Autowired
    private VNPayService vnPayService;

    @PostMapping("/vnpay/create")
    public ApiResponse<PaymentResponse> createPayment(@Valid @RequestBody PaymentRequest request, HttpServletRequest httpRequest) {
        return ApiResponse.<PaymentResponse>builder()
                .result(vnPayService.createPayment(request, httpRequest))
                .build();
    }

    @GetMapping("/vnpay/callback")
    public ApiResponse<String> paymentCallback(HttpServletRequest request) {
        boolean success = vnPayService.processPaymentCallback(request);
        return ApiResponse.<String>builder()
                .result(success ? "Payment successful" : "Payment failed")
                .build();
    }
}
