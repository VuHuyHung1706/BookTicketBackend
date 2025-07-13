package com.web.backend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Invalid input", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User already exists", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED(1010, "Email already exists", HttpStatus.BAD_REQUEST),
    INVALID_USERNAME(1003, "Username must be at least 4 characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Password must be at least 8 characters", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User not found", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Authentication failed", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    PERMISSION_NOT_EXISTED(1008, "Permission not found", HttpStatus.NOT_FOUND),
    USER_NOT_ACTIVE(1009, "User account is not active", HttpStatus.FORBIDDEN),

    USERNAME_NOT_BLANK(9000, "Username cannot be blank", HttpStatus.BAD_REQUEST),
    PASSWORD_NOT_BLANK(9001, "Password cannot be blank", HttpStatus.BAD_REQUEST),
    FIRST_NAME_NOT_BLANK(9002, "First name cannot be blank", HttpStatus.BAD_REQUEST),
    LAST_NAME_NOT_BLANK(9003, "Last name cannot be blank", HttpStatus.BAD_REQUEST);


    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
