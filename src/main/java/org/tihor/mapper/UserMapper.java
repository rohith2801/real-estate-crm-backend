package org.tihor.mapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.tihor.entity.UserEntity;
import org.tihor.enums.UserType;
import org.tihor.model.request.UserRequest;
import org.tihor.model.response.CustomerResponse;
import org.tihor.model.response.LoginResponse;

/**
 * The type User mapper.
 */
@Component
public class UserMapper {
    /**
     * The Password encoder.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Map request to entity user entity.
     *
     * @param request  the request
     * @param userType the user type
     * @return the user entity
     */
    public UserEntity mapRequestToEntity(final UserRequest request, final UserType userType) {
        return mapRequestToEntity(request, new UserEntity())
                .toBuilder()
                .password(passwordEncoder.encode("P@ssw0rd"))
                .userType(userType)
                .isDeleted(false)
                .build();
    }

    /**
     * Map request to entity user entity.
     *
     * @param request the request
     * @param entity  the entity
     * @return the user entity
     */
    public UserEntity mapRequestToEntity(final UserRequest request, final UserEntity entity) {
        return entity.toBuilder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .street(request.getStreet())
                .pinCode(request.getPinCode())
                .location(request.getLocation())
                .cellPhone(request.getCellPhone())
                .emailId(request.getEmailId())
                .username(StringUtils.isNotEmpty(request.getUsername()) ? request.getUsername() : request.getEmailId())
                .build();
    }

    /**
     * Map entity to response customer response.
     *
     * @param entity the entity
     * @return the customer response
     */
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
                .username(entity.getUsername())
                .build();
    }

    /**
     * Map entity to login response login response.
     *
     * @param entity the entity
     * @param token  the token
     * @return the login response
     */
    public LoginResponse mapEntityToLoginResponse(final UserEntity entity, final String token) {
        var response = new LoginResponse(token);

        response.setId(entity.getId());
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        response.setStreet(entity.getStreet());
        response.setPinCode(entity.getPinCode());
        response.setLocation(entity.getLocation());
        response.setCellPhone(entity.getCellPhone());
        response.setEmailId(entity.getEmailId());
        response.setUsername(entity.getUsername());

        return response;
    }
}
