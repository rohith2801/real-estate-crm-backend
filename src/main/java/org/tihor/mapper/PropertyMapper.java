package org.tihor.mapper;

import org.springframework.stereotype.Component;
import org.tihor.entity.CustomerEntity;
import org.tihor.entity.PropertyEntity;
import org.tihor.model.request.PropertyRequest;

import java.time.LocalDate;

/**
 * The type Property mapper.
 */
@Component
public class PropertyMapper {
    /**
     * Map request to entity property entity.
     *
     * @param request        the request
     * @param customerEntity the customer entity
     * @return the property entity
     */
    public PropertyEntity mapRequestToEntity(final PropertyRequest request, final CustomerEntity customerEntity) {
        return PropertyEntity.builder()
                .customerEntity(customerEntity)
                .street(request.getStreet())
                .pinCode(request.getPinCode())
                .location(request.getLocation())
                .apartmentNumber(request.getApartmentNumber())
                .size(request.getSize())
                .type(request.getType())
                .isReadyToSell(request.getIsReadyToSell())
                .quotedPrice(request.getQuotedPrice())
                .isSold(request.getIsSold())
                .soldPrice(request.getSoldPrice())
                .soldDate(request.getSoldDate())
                .createdDate(LocalDate.now())
                .build();
    }
}
