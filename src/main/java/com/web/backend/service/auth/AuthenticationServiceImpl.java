package com.web.backend.service.auth;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.web.backend.dto.request.auth.AuthenticationRequest;
import com.web.backend.dto.request.auth.IntrospectRequest;
import com.web.backend.dto.request.auth.LogoutRequest;
import com.web.backend.dto.request.auth.RefreshRequest;
import com.web.backend.dto.response.auth.AuthenticationResponse;
import com.web.backend.dto.response.auth.IntrospectResponse;
import com.web.backend.entity.Account;
import com.web.backend.entity.InvalidatedToken;
import com.web.backend.exception.AppException;
import com.web.backend.exception.ErrorCode;
import com.web.backend.repository.AccountRepository;
import com.web.backend.repository.InvalidatedTokenRepository;
import com.web.backend.repository.ManagerRepository;
import jakarta.transaction.Transactional;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long VALID_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected long REFRESHABLE_DURATION;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private InvalidatedTokenRepository invalidatedTokenRepository;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Account account = accountRepository.findByUsername(request.getUsername()).orElseThrow(()
                -> new AppException(ErrorCode.USER_NOT_EXISTED));

        if (!account.getStatus()) {
            throw new AppException(ErrorCode.USER_NOT_ACTIVE);
        }

        Boolean authenticated = passwordEncoder.matches(request.getPassword(), account.getPassword());

        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        String token = generateJwtToken(account);
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        boolean isValid = true;
        try {
            verifyToken(token, false);
        }
        catch (AppException e) {
            isValid = false;
        }

        return IntrospectResponse.builder()
                .valid(isValid)
                .build();

    }

    @Override
    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        try{
            var signToken = verifyToken(request.getToken(), true);

            String jit = signToken.getJWTClaimsSet().getJWTID();
            Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

            InvalidatedToken invalidatedToken =
                    InvalidatedToken.builder().id(jit).expiryTime(expiryTime).build();

            invalidatedTokenRepository.save(invalidatedToken);
        }
        catch (AppException e) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
    }

    @Override
    public AuthenticationResponse refresh(RefreshRequest request) throws ParseException, JOSEException {
        var signedJWT = verifyToken(request.getToken(), true);

        var jit = signedJWT.getJWTClaimsSet().getJWTID();
        var expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken =
                InvalidatedToken.builder().id(jit).expiryTime(expiryTime).build();

        invalidatedTokenRepository.save(invalidatedToken);

        var username = signedJWT.getJWTClaimsSet().getSubject();

        var user =
                accountRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        var token = generateJwtToken(user);

        return AuthenticationResponse.builder().token(token).authenticated(true).build();
    }

    public SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = (isRefresh)
                ? new Date(signedJWT
                .getJWTClaimsSet()
                .getIssueTime()
                .toInstant()
                .plus(REFRESHABLE_DURATION, ChronoUnit.DAYS)
                .toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        if (!(verified && expiryTime.after(new Date()))) throw new AppException(ErrorCode.UNAUTHENTICATED);

        if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        return signedJWT;
    }

    public String generateJwtToken(Account account) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(account.getUsername())
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(VALID_DURATION, ChronoUnit.DAYS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(account))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        }
        catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    public String buildScope(Account account) {
        StringJoiner stringJoiner = new StringJoiner(" ");

        var manager = managerRepository.findByAccountUsername(account.getUsername());
        if (manager.isPresent()) {
            stringJoiner.add("ROLE_MANAGER");
        } else {
            stringJoiner.add("ROLE_CUSTOMER");
        }

        return stringJoiner.toString();
    }
}
