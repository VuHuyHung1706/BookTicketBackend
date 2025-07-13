package com.web.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "managers")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Email(message = "Invalid email format")
    @Column(name = "email")
    private String email;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "gender")
    private Boolean gender; // true = Male, false = Female
    
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "id_card")
    private String idCard;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ToString.Exclude
    private Account account;
}
