package org.tihor.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.tihor.entity.CustomerEntity;
import org.tihor.entity.PropertyEntity;
import org.tihor.model.request.PropertyRequest;
import org.tihor.model.response.PropertyResponse;

import java.time.LocalDate;
import java.util.List;

/**
 * The type Property mapper.
 */
@Component
@RequiredArgsConstructor
public class PropertyMapper {
    /**
     * The Property lending partner mapper.
     */
    private final PropertyLendingPartnerMapper propertyLendingPartnerMapper;

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
                .propertyNumber(request.getPropertyNumber())
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

    /**
     * Map entity to response property response.
     *
     * @param entity the entity
     * @return the property response
     */
    public PropertyResponse mapEntityToResponse(final PropertyEntity entity) {
        return PropertyResponse.builder()
                .id(entity.getId())
                .street(entity.getStreet())
                .pinCode(entity.getPinCode())
                .location(entity.getLocation())
                .propertyNumber(entity.getPropertyNumber())
                .size(entity.getSize())
                .type(entity.getType())
                .isReadyToSell(entity.getIsReadyToSell())
                .quotedPrice(entity.getQuotedPrice())
                .isSold(entity.getIsSold())
                .soldPrice(entity.getSoldPrice())
                .soldDate(entity.getSoldDate())
                .createdDate(entity.getCreatedDate())
                .lendingPartnerResponses(propertyLendingPartnerMapper.mapEntitiesToResponses(entity.getPropertyLendingPartnerEntities()))
                .build();
    }

    /**
     * Map entities to responses list.
     *
     * @param entities the entities
     * @return the list
     */
    public List<PropertyResponse> mapEntitiesToResponses(final List<PropertyEntity> entities) {
        return entities.stream()
                .map(this::mapEntityToResponse)
                .toList();
    }
}
