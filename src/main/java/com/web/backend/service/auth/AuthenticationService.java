package com.web.backend.service.auth;

import com.nimbusds.jose.JOSEException;
import com.web.backend.dto.request.auth.AuthenticationRequest;
import com.web.backend.dto.request.auth.IntrospectRequest;
import com.web.backend.dto.request.auth.LogoutRequest;
import com.web.backend.dto.request.auth.RefreshRequest;
import com.web.backend.dto.response.auth.AuthenticationResponse;
import com.web.backend.dto.response.auth.IntrospectResponse;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;
    void logout(LogoutRequest request) throws ParseException, JOSEException;
    AuthenticationResponse refresh(RefreshRequest request) throws ParseException, JOSEException;
}
