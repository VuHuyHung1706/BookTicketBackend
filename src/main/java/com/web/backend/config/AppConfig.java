package com.web.backend.config;

import com.web.backend.entity.Account;
import com.web.backend.entity.Manager;
import com.web.backend.repository.AccountRepository;
import com.web.backend.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    @Autowired
    PasswordEncoder passwordEncoder;

    static final String ADMIN_USER_NAME = "admin";
    static final String ADMIN_PASSWORD = "admin";

    @Bean
    @ConditionalOnProperty(
            name = "spring.datasource.driver-class-name",
            havingValue = "com.mysql.cj.jdbc.Driver"
    )
    ApplicationRunner applicationRunner(AccountRepository accountRepository, ManagerRepository managerRepository) {
        return args -> {
            if (accountRepository.findByUsername(ADMIN_USER_NAME).isEmpty()) {
                // Create admin account
                Account adminAccount = Account.builder()
                        .username(ADMIN_USER_NAME)
                        .password(passwordEncoder.encode(ADMIN_PASSWORD))
                        .status(true)
                        .build();

                adminAccount = accountRepository.save(adminAccount);

                Manager adminManager = Manager.builder()
                        .firstName("Admin")
                        .lastName("System")
                        .account(adminAccount)
                        .build();

                managerRepository.save(adminManager);
            }
        };
    }
}
