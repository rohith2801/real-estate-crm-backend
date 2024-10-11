package org.tihor.mapper;

import org.springframework.stereotype.Component;
import org.tihor.entity.UserEntity;
import org.tihor.enums.UserType;
import org.tihor.model.request.UserRequest;
import org.tihor.model.response.CustomerResponse;

@Component
public class UserMapper {
    public UserEntity mapRequestToEntity(final UserRequest request, final UserType userType) {
        return mapRequestToEntity(request, new UserEntity())
                .toBuilder()
                .password("P@ssw0rd")
                .userType(userType)
                .isDeleted(false)
                .build();
    }

    public UserEntity mapRequestToEntity(final UserRequest request, final UserEntity entity) {
        return entity.toBuilder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .street(request.getStreet())
                .pinCode(request.getPinCode())
                .location(request.getLocation())
                .cellPhone(request.getCellPhone())
                .emailId(request.getEmailId())
                .build();
    }

    public CustomerResponse mapEntityToResponse(final UserEntity entity) {
        return CustomerResponse.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .street(entity.getStreet())
                .pinCode(entity.getPinCode())
                .location(entity.getLocation())
                .cellPhone(entity.getCellPhone())
                .emailId(entity.getEmailId())
                .build();
    }
}
