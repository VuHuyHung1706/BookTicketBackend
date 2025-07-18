package com.web.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CinemaRequest {

    @NotBlank(message = "Cinema name cannot be blank")
    private String name;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    private String phone;
}
