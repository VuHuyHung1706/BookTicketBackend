package com.web.backend.mapper;

import com.web.backend.dto.request.UpdateProfileRequest;
import com.web.backend.dto.request.UserRegistrationRequest;
import com.web.backend.dto.response.CustomerResponse;
import com.web.backend.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomer(UserRegistrationRequest request);

    @Mapping(source = "account.username", target = "username")
    @Mapping(source = "account.status", target = "status")
    CustomerResponse toCustomerResponse(Customer customer);

    void updateCustomer(@MappingTarget Customer customer, UpdateProfileRequest request);
}
