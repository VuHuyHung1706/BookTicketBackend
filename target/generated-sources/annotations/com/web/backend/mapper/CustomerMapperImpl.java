package com.web.backend.mapper;

import com.web.backend.dto.request.UpdateProfileRequest;
import com.web.backend.dto.request.UserRegistrationRequest;
import com.web.backend.dto.response.CustomerResponse;
import com.web.backend.entity.Account;
import com.web.backend.entity.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-28T03:48:43+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 24.0.1 (Amazon.com Inc.)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer toCustomer(UserRegistrationRequest request) {
        if ( request == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.firstName( request.getFirstName() );
        customer.lastName( request.getLastName() );
        customer.email( request.getEmail() );
        customer.phone( request.getPhone() );
        customer.gender( request.getGender() );
        customer.dateOfBirth( request.getDateOfBirth() );
        customer.address( request.getAddress() );

        return customer.build();
    }

    @Override
    public CustomerResponse toCustomerResponse(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerResponse.CustomerResponseBuilder customerResponse = CustomerResponse.builder();

        customerResponse.username( customerAccountUsername( customer ) );
        customerResponse.status( customerAccountStatus( customer ) );
        customerResponse.id( customer.getId() );
        customerResponse.firstName( customer.getFirstName() );
        customerResponse.lastName( customer.getLastName() );
        customerResponse.email( customer.getEmail() );
        customerResponse.phone( customer.getPhone() );
        customerResponse.gender( customer.getGender() );
        customerResponse.dateOfBirth( customer.getDateOfBirth() );
        customerResponse.address( customer.getAddress() );

        return customerResponse.build();
    }

    @Override
    public void updateCustomer(Customer customer, UpdateProfileRequest request) {
        if ( request == null ) {
            return;
        }

        customer.setFirstName( request.getFirstName() );
        customer.setLastName( request.getLastName() );
        customer.setEmail( request.getEmail() );
        customer.setPhone( request.getPhone() );
        customer.setGender( request.getGender() );
        customer.setDateOfBirth( request.getDateOfBirth() );
        customer.setAddress( request.getAddress() );
    }

    private String customerAccountUsername(Customer customer) {
        Account account = customer.getAccount();
        if ( account == null ) {
            return null;
        }
        return account.getUsername();
    }

    private Boolean customerAccountStatus(Customer customer) {
        Account account = customer.getAccount();
        if ( account == null ) {
            return null;
        }
        return account.getStatus();
    }
}
