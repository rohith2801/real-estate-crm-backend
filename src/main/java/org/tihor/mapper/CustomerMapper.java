package org.tihor.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.tihor.entity.CustomerEntity;
import org.tihor.model.request.CustomerRequest;
import org.tihor.model.response.CustomerResponse;

/**
 * The type Customer mapper.
 */
@Component
@RequiredArgsConstructor
public class CustomerMapper {
    /**
     * The Property mapper.
     */
    private final PropertyMapper propertyMapper;

    /**
     * Map request to entity customer entity.
     *
     * @param request the request
     * @return the customer entity
     */
    public CustomerEntity mapRequestToEntity(final CustomerRequest request) {
        return mapRequestToEntity(request, new CustomerEntity());
    }

    /**
     * Map request to entity customer entity.
     *
     * @param request the request
     * @param entity  the entity
     * @return the customer entity
     */
    public CustomerEntity mapRequestToEntity(final CustomerRequest request, final CustomerEntity entity) {
        return entity.toBuilder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .street(request.getStreet())
                .pinCode(request.getPinCode())
                .location(request.getLocation())
                .cellPhone(request.getCellPhone())
                .emailId(request.getEmailId())
                .isDeleted(false)
                .build();
    }

    /**
     * Map entity to response customer response.
     *
     * @param entity the entity
     * @return the customer response
     */
    public CustomerResponse mapEntityToResponse(final CustomerEntity entity) {
        return mapEntityToResponse(entity, false);
    }

    /**
     * Map entity to response customer response.
     *
     * @param entity              the entity
     * @param withPropertyDetails the with property details
     * @return the customer response
     */
    public CustomerResponse mapEntityToResponse(final CustomerEntity entity, final Boolean withPropertyDetails) {
        var builder = CustomerResponse.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .street(entity.getStreet())
                .pinCode(entity.getPinCode())
                .location(entity.getLocation())
                .cellPhone(entity.getCellPhone())
                .emailId(entity.getEmailId());

        if (withPropertyDetails) {
            var properties = propertyMapper.mapEntitiesToResponses(entity.getPropertyEntities());
            builder.propertyResponses(properties);
        }

        return builder.build();
    }
}
