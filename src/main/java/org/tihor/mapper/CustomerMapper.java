package org.tihor.mapper;

import org.springframework.stereotype.Component;
import org.tihor.entity.CustomerEntity;
import org.tihor.model.request.CustomerRequest;

/**
 * The type Customer mapper.
 */
@Component
public class CustomerMapper {
    /**
     * Map request to entity customer entity.
     *
     * @param request the request
     * @return the customer entity
     */
    public CustomerEntity mapRequestToEntity(final CustomerRequest request) {
        return CustomerEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .street(request.getStreet())
                .pinCode(request.getPinCode())
                .location(request.getLocation())
                .cellPhone(request.getCellPhone())
                .emailId(request.getEmailId())
                .build();
    }
}
