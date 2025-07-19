package com.web.backend.mapper;

import com.web.backend.dto.response.ManagerResponse;
import com.web.backend.entity.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ManagerMapper {

    @Mapping(source = "account.username", target = "username")
    @Mapping(source = "account.status", target = "status")
    ManagerResponse toManagerResponse(Manager manager);
}
