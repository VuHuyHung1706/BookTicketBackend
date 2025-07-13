package com.web.backend.controller;

import com.web.backend.dto.request.*;
import com.web.backend.dto.response.*;
import com.web.backend.service.account.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public ApiResponse<CustomerResponse> registerUser(@Valid @RequestBody UserRegistrationRequest request) {
        return ApiResponse.<CustomerResponse>builder()
                .result(accountService.registerUser(request))
                .build();
    }

    @PostMapping("/change-password")
    public ApiResponse<String> changePassword(@Valid @RequestBody ChangePasswordRequest request) {
        accountService.changePassword(request);
        return ApiResponse.<String>builder()
                .result("Password changed successfully")
                .build();
    }

    @PutMapping("/profile")
    public ApiResponse<CustomerResponse> updateProfile(@Valid @RequestBody UpdateProfileRequest request) {
        return ApiResponse.<CustomerResponse>builder()
                .result(accountService.updateProfile(request))
                .build();
    }

    @GetMapping("/profile")
    public ApiResponse<CustomerResponse> getMyProfile() {
        return ApiResponse.<CustomerResponse>builder()
                .result(accountService.getMyProfile())
                .build();
    }
}
