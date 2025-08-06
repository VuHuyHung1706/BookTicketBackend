package com.web.backend.controller;

import com.nimbusds.jose.JOSEException;
import com.web.backend.dto.request.auth.AuthenticationRequest;
import com.web.backend.dto.request.auth.IntrospectRequest;
import com.web.backend.dto.request.auth.LogoutRequest;
import com.web.backend.dto.request.auth.RefreshRequest;
import com.web.backend.dto.response.ApiResponse;
import com.web.backend.dto.response.auth.AuthenticationResponse;
import com.web.backend.dto.response.auth.IntrospectResponse;
import com.web.backend.service.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/google/login")
    public ApiResponse<AuthenticationResponse> googleLogin(@RequestParam String code) {
        var result = authenticationService.googleLogin(code);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/refresh")
    public ApiResponse<AuthenticationResponse> refresh(@RequestBody RefreshRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.refresh(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }


    @PostMapping("/logout")
    public ApiResponse<String> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<String>builder()
                .result("Logout successful")
                .build();
    }
}
