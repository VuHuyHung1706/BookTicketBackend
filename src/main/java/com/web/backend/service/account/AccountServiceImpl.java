package com.web.backend.service.account;

import com.web.backend.dto.request.*;
import com.web.backend.dto.response.*;
import com.web.backend.entity.Account;
import com.web.backend.entity.Customer;
import com.web.backend.entity.Manager;
import com.web.backend.exception.AppException;
import com.web.backend.exception.ErrorCode;
import com.web.backend.mapper.CustomerMapper;
import com.web.backend.mapper.ManagerMapper;
import com.web.backend.repository.AccountRepository;
import com.web.backend.repository.CustomerRepository;
import com.web.backend.repository.ManagerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private ManagerMapper managerMapper;

    @Override
    public CustomerResponse registerUser(UserRegistrationRequest request) {
        if (accountRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        if (request.getEmail() != null && customerRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }

        Account account = Account.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .status(true)
                .build();

        account = accountRepository.save(account);

        Customer customer = customerMapper.toCustomer(request);
        customer.setAccount(account);

        customer = customerRepository.save(customer);

        return customerMapper.toCustomerResponse(customer);
    }

    @Override
    public void changePassword(ChangePasswordRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        if (!passwordEncoder.matches(request.getCurrentPassword(), account.getPassword())) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        account.setPassword(passwordEncoder.encode(request.getNewPassword()));
        accountRepository.save(account);
    }

    @Override
    public CustomerResponse updateProfile(UpdateProfileRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        
        Customer customer = customerRepository.findByAccountUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        if (request.getEmail() != null && customerRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }

        customerMapper.updateCustomer(customer, request);
        customer = customerRepository.save(customer);

        return customerMapper.toCustomerResponse(customer);
    }

    @Override
    public CustomerResponse getMyProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        
        Customer customer = customerRepository.findByAccountUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return customerMapper.toCustomerResponse(customer);
    }

    @Override
    public ManagerResponse getManager() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Manager manager = managerRepository.findByAccountUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return managerMapper.toManagerResponse(manager);
    }
}
