package com.web.backend.mapper;

import com.web.backend.dto.response.ManagerResponse;
import com.web.backend.entity.Account;
import com.web.backend.entity.Manager;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-13T17:06:09+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Homebrew)"
)
@Component
public class ManagerMapperImpl implements ManagerMapper {

    @Override
    public ManagerResponse toManagerResponse(Manager manager) {
        if ( manager == null ) {
            return null;
        }

        ManagerResponse.ManagerResponseBuilder managerResponse = ManagerResponse.builder();

        managerResponse.username( managerAccountUsername( manager ) );
        managerResponse.status( managerAccountStatus( manager ) );
        managerResponse.id( manager.getId() );
        managerResponse.firstName( manager.getFirstName() );
        managerResponse.lastName( manager.getLastName() );
        managerResponse.email( manager.getEmail() );
        managerResponse.phone( manager.getPhone() );
        managerResponse.gender( manager.getGender() );
        managerResponse.dateOfBirth( manager.getDateOfBirth() );
        managerResponse.address( manager.getAddress() );
        managerResponse.idCard( manager.getIdCard() );

        return managerResponse.build();
    }

    private String managerAccountUsername(Manager manager) {
        Account account = manager.getAccount();
        if ( account == null ) {
            return null;
        }
        return account.getUsername();
    }

    private Boolean managerAccountStatus(Manager manager) {
        Account account = manager.getAccount();
        if ( account == null ) {
            return null;
        }
        return account.getStatus();
    }
}
