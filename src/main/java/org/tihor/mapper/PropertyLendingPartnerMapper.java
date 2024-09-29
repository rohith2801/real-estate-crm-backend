package org.tihor.mapper;

import org.springframework.stereotype.Component;
import org.tihor.entity.LendingPartnerEntity;
import org.tihor.entity.PropertyEntity;
import org.tihor.entity.PropertyLendingPartnerEntity;
import org.tihor.model.request.PropertyLendingPartnerRequest;

/**
 * The type Property lending partner mapper.
 */
@Component
public class PropertyLendingPartnerMapper {
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
}
