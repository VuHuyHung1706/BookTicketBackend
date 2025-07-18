package com.web.backend.dto.response.actor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActorResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private Boolean gender;
    private LocalDate dateOfBirth;
    private String nationality;
}
