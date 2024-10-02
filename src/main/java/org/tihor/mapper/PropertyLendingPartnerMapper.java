package org.tihor.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.tihor.entity.LendingPartnerEntity;
import org.tihor.entity.PropertyEntity;
import org.tihor.entity.PropertyLendingPartnerEntity;
import org.tihor.model.request.PropertyLendingPartnerRequest;
import org.tihor.model.response.PropertyLendingPartnerResponse;

import java.util.List;

/**
 * The type Property lending partner mapper.
 */
@Component
@RequiredArgsConstructor
public class PropertyLendingPartnerMapper {
    /**
     * The Lending partner mapper.
     */
    private final LendingPartnerMapper lendingPartnerMapper;

    /**
     * Map request to entity property lending partner entity.
     *
     * @param request              the request
     * @param propertyEntity       the property entity
     * @param lendingPartnerEntity the lending partner entity
     * @return the property lending partner entity
     */
    public PropertyLendingPartnerEntity mapRequestToEntity(
            final PropertyLendingPartnerRequest request,
            final PropertyEntity propertyEntity,
            final LendingPartnerEntity lendingPartnerEntity
    ) {
        return PropertyLendingPartnerEntity.builder()
                .propertyEntity(propertyEntity)
                .lendingPartnerEntity(lendingPartnerEntity)
                .loanAmount(request.getLoanAmount())
                .rateOfInterest(request.getRateOfInterest())
                .tenureInMonths(request.getTenureInMonths())
                .redemption(request.getRedemption())
                .rate(request.getRate())
                .notes(request.getNotes())
                .build();
    }

    /**
     * Map entity to response property lending partner response.
     *
     * @param entity the entity
     * @return the property lending partner response
     */
    public PropertyLendingPartnerResponse mapEntityToResponse(final PropertyLendingPartnerEntity entity) {
        return PropertyLendingPartnerResponse.builder()
                .id(entity.getId())
                .loanAmount(entity.getLoanAmount())
                .rateOfInterest(entity.getRateOfInterest())
                .tenureInMonths(entity.getTenureInMonths())
                .redemption(entity.getRedemption())
                .rate(entity.getRate())
                .notes(entity.getNotes())
                .lendingPartnerResponse(lendingPartnerMapper.mapEntityToResponse(entity.getLendingPartnerEntity()))
                .build();
    }

    /**
     * Map entities to responses list.
     *
     * @param entities the entities
     * @return the list
     */
    public List<PropertyLendingPartnerResponse> mapEntitiesToResponses(final List<PropertyLendingPartnerEntity> entities) {
        return entities.stream().map(this::mapEntityToResponse).toList();
    }
}
